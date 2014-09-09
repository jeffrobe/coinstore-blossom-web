package com.coin.web.service.impl;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coin.web.common.Config;
import com.coin.dao.RoleDao;
import com.coin.dao.UserDao;
import com.coin.dao.LoginDao;
import com.coin.dao.FlaggeduserDao;
import com.coin.web.service.PropertiesService;
import com.coin.web.model.CustomUserDetail;
import com.coin.model.Email;
import com.coin.model.Login;
import com.coin.model.Message;
 
import com.coin.model.Role;
import com.coin.model.User;
import com.coin.web.service.EmailService;
import com.coin.web.service.RegistrationService;
 
@Service ("registrationService")
public class RegistrationServiceImpl implements RegistrationService {
	private static final Logger log = Logger.getLogger(RegistrationService.class);
 
	@Autowired
	private LoginDao loginDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private FlaggeduserDao flaggeduserDao;

	@Autowired
	EmailService emailService;

	@Autowired
	protected PropertiesService propertiesService;
	
	
	public RegistrationServiceImpl (  ) {
		 
	}

	@Override
	public Message updateProfile ( User user ) {
      
       if ( flaggeduserDao.isUserFlagged(user.getFirstname(), user.getLastname() ) ) 
    	   return new Message("error","flagged_user"); 
       
       user.setUpdated(new Date());
       userDao.update(user);
       
       return new Message("success",""); 
	}
	
	@Override
	public Message updateProfilePhone (  Long userId, String phone ) {
	   User user = userDao.findById(userId);
  
       user.setPhone(phone);
       
       user.setUpdated(new Date());
       userDao.update(user);
       
       return new Message("success",""); 
	}
	
	@Override
	public Message resetPassword (Long userId, String password) {
		User user = userDao.findById(userId);
		
		if (user==null) return new Message("error","login.user.notfound");	
		if (!user.getStatus().equals("active")) return new Message("error","login.user.notactive"); 

		user.setUpdated(new Date());
		user.setPassword(getEncodedPassword(password));
		user.setPasswordchanged(new Date());
		userDao.update(user);

		return new Message("success",""); 
	}
	
	
	@Override
	public Message recordLogin (CustomUserDetail customUserDetail, String code) {
		Login login = new Login(customUserDetail.getId(), customUserDetail.getIp(), code);
	
		loginDao.save(login);
		
		Message msg = new Message("success","");
		return msg; 
	}
	
	@Override
	public Message validateLogin (CustomUserDetail customUserDetail, String email, String password) {
		
        if (customUserDetail == null) {
        	log.info("Username not found.");
        	return new Message("error","login.user.notfound");
        }
 
        String passwordEnc = this.getEncodedPassword (password);
    	if (!passwordEnc.equals(customUserDetail.getPassword())) {
        	log.info("Wrong password");
        	return new Message("error","login.password.invalid");
        }
         
		if (!customUserDetail.getStatus().equals(Config.statusActive)) {
			if ( customUserDetail.getStatus().equals(Config.statusPending) ) {
				Message m = this.forgotPassword(email);
				if (m.getStatus().equals(Config.MSG_SUCCESS)) return new Message("error","login.user.notactive");
				else  return new Message("error","login.user.notactive.error");
			}
			return new Message("error","login.user.badstatus"); 
		}
  
		
		User user = userDao.getUserObj(email);
				
		Long nFailUser = loginDao.getLoginsUser( user.getId(), "fail",  propertiesService.getUserFailLoginPeriodMinutes()); 
		if (nFailUser >= propertiesService.getUserMaxFailedLoginsPeriodCount() ) 
			return new Message("error","login.user.toomanyfaileduserlogins"); 
	
		Long periodUserLogin = propertiesService.getUserLoginPeriodMinutes();
		Long nSuccessUser = loginDao.getLoginsUser( user.getId(), null,  periodUserLogin);
		if (nSuccessUser >= propertiesService.getUserMaxLoginsPeriodCount() ) 
			return new Message("error","login.user.toomanyuserlogins"); 
	
		Long nfail = loginDao.getLogins( "fail",  propertiesService.getFailLoginPeriodMinutes());
		if (nfail >= propertiesService.getMaxFailedLoginsPeriodCount() ) 
			return new Message("error","login.user.toomanyfaillogins"); 
	
		
		Long periodLogin = propertiesService.getLoginPeriodMinutes();
		Long nSuccess = loginDao.getLogins( null,  periodLogin);
		if (nSuccess >= propertiesService.getMaxLoginsPeriodCount() ) 
			return new Message("error","login.user.toomanylogins"); 
		 
		Message msg = new Message("success","");
		msg.setExtra(customUserDetail);
		return msg; 
	}

	 
	@Override
	public Message validateToken (String token) {
		
        if (propertiesService.getRequireTokenAuth()) { 
			String tokenVal = null;
			if(propertiesService.getOverrideTokenAuth()) tokenVal = propertiesService.getOverrideTokenAuthValue();
			else tokenVal = "test";
			
	        if (token==null || !token.equals(tokenVal)) {
	        	log.info("bad token");
	        	return new Message(Config.MSG_ERROR,"token.invalid");
	        }
        }
        
		return new Message(Config.MSG_SUCCESS,""); 
	}

	
	@Override
	public Message autoLogin (Long userId) {
 
		return null; 
	}
	
	@Override
	public Message getUserFromHash (String email, String hash ){
		if (email == null || email.isEmpty()) return new Message("error","bad or missing email");
		if (hash == null || hash.isEmpty()) return new Message("error","bad or missing hash");
	
		User user = userDao.findByEmail(email);
		if (user==null) return new Message("error","bad or missing user");
		log.info("user: "+user.getEmail());
		
		boolean stat = isValidRegistrationHash(user, hash);
		if (!stat) return new Message("error","bad hash"); 

		
		Message msg  = new Message("success",user);
		msg.setExtra(user);
		return msg;
	}
	
	
	@Override
	public Message getRegisteredUser (String email){
		User user = userDao.getUserObj(email);
		if (user==null) return new Message(Config.MSG_ERROR,"bad or missing user");
		log.info("user: "+user.getEmail());
		
		Message msg  = new Message(Config.MSG_SUCCESS,user);
		msg.setExtra(user);
		return msg;
	}


	
	@Override
	public Message confirmNewUserTos (Long userId) {
		User user = userDao.findById(userId);
		if (user == null) return new Message(Config.MSG_ERROR, "login.user.notfound"); 
		//if (user.getStatus().equals("active")) return new Message("success","already_confirmed"); 
		
		user.setTosconfirm(new Date());
		userDao.update(user);

		return new Message(Config.MSG_SUCCESS, (Object) user); 
	}

	@Override
	public Message newUserActive (Long userId) {
		User user = userDao.findById(userId);
		user.setStatus(Config.statusUserActive);

	
		Set<Role> roles = new HashSet<Role>();
		Role role = roleDao.findByName("ROLE_USER");
		roles.add(role);
		user.setRoles(roles);

		userDao.update(user);
		return new Message("success", (Object) user); 
	}
	
	@Override
	public Message confirmUserTos (   String email ) {
		User user = userDao.findByEmail(email);
		
		if (user==null) return new Message("error","bad or missing user");
		
	 	user.setTosconfirm(new Date());
	 	userDao.update(user);
		return new Message("success",""); 
	}
	
	@Override
	public Message forgotPassword (String email ) {
		User user = userDao.findByEmail(email);
		if(user==null) return new Message (0, "error", "forgotpassword.user.notfound");
		
		log.info("forgot password status:"+user.getStatus());
		Map<String, Object> data=null;
	    String regHash = "test";	    
	    String templateName="";
		try {
			regHash = getEmailRegistrationHash(user);
		} catch (Exception e) {
			e.printStackTrace();
			return new Message (0, "error", "error creating reg hash");
		}  

		if (user.getStatus().equals(Config.statusUserPending)) {
			data = getRegistrationConfirmData(user, regHash);
			templateName = Config.emailRegistrationConfirmTemplate;

		}
	
		else if (user.getStatus().equals(Config.statusUserActive)) {
			templateName = Config.emailForgotPasswordTemplate;	
			data = getForgotPasswordData(user, regHash);
		}

		else {
			return new Message (0, "error", "user is in bad status: "+user.getStatus());
		}
		Email emailObj = emailService.getEmailFromTemplate(templateName, data);
		log.debug("emailObj: "+emailObj.getBodyHtml());
		emailService.send(emailObj, user.getEmail(), propertiesService.getSendHtml());
		
		return new Message(1, "success", "success forgot password sent", user);
	}
	    
	@Override
	public Message newRegistration (String email, String password) {

		boolean exists = userDao.userExists (email);
		if(exists) return new Message (Config.MSG_ERROR, "user already exists");
		
		User userTmp = new User(email, getEncodedPassword(password));
		userTmp.setStatus(Config.statusUserPending);
		userTmp.setEntered( new Date());
		userTmp.setUpdated( new Date());
		
		userDao.save(userTmp);
		
		User user = userDao.findByEmail(email);
		log.info("registered user status:"+user.getStatus());
	    	    	
	    String regHash = "";
		try {
			regHash = getEmailRegistrationHash(user);
		} catch (Exception e) {
			e.printStackTrace();
			return new Message (Config.MSG_ERROR, "error creating reg hash");
		}  
	   
		Map<String, Object> data = getRegistrationConfirmData(user, regHash);
		Email emailObj = emailService.getEmailFromTemplate(Config.emailRegistrationConfirmTemplate, data);
		log.debug("emailObj: "+emailObj.getBodyHtml());

		emailService.send(emailObj, user.getEmail(), propertiesService.getSendHtml());
		
		return new Message(1,Config.MSG_SUCCESS, "success user registered", user);
	}
	    
	
    @Override
    public String getEmailRegistrationHash (User user)  {
    	String str = user.getEmail()+user.getEntered().getTime();
    	log.debug("str: "+str);

    	String encode = md5encode(str);
    
    	return encode;
    }
    
    private String md5encode(String str) {
    	log.debug("str: "+str);

    	MessageDigest m=null;
		try {
			m = MessageDigest.getInstance("md5");
		} catch (Exception e) {
			log.error("error encodeign"+e.getMessage());
			return null;
		}
    	byte[] data = str.getBytes(); 
    	m.update(data,0,data.length);
    	BigInteger i = new BigInteger(1,m.digest());
    	String encode = String.format("%1$032X", i);
    	log.debug("encode: "+encode);
    	
    	return encode.toLowerCase();
    }
    
    @Override
    public String getEncodedPassword (String str) {     
    	return md5encode(str);
    }
  
    @Override 
    public Map<String, Object> getForgotPasswordData (User user,  String hash) {
    	String url= propertiesService.getBaseUrl()+propertiesService.getEmailForogotpasswordUrl();
    	 
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("hash", hash);
		model.put("url", url);
		model.put("user", user);
		model.put("email", user.getEmail());
		
		return model;
 
    }
 
    
    @Override 
    public Map<String, Object> getRegistrationConfirmData (User user,  String hash) {
    	String url= propertiesService.getBaseUrl()+propertiesService.getEmailRegistrationUrl();
    	 
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("hash", hash);
		model.put("url", url);
		model.put("user", user);
		model.put("email", user.getEmail());
		
		return model;
 
    }
 
    @Override
    public Boolean isValidRegistrationHash (User user, String str) {
    	String encode=null;
		try {
			encode = this.getEmailRegistrationHash (user);
		} catch ( Exception e) {
			log.error("encode error "+e.getMessage());
			return false;
		} 
		
    	if(str.equals(encode)) return true;
    	return false;
    	 
    }

 
 
}
