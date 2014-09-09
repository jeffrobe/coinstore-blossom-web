package com.coin.web.dao;
 
 
import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;

import com.coin.web.common.Config;
import com.coin.dao.UserDao;
import com.coin.dao.FlaggeduserDao;
import com.coin.web.forms.ProfileForm;
import com.coin.model.Email;
import com.coin.model.Flaggeduser;
import com.coin.model.Message;
import com.coin.model.Notificationtype;
import com.coin.model.Role;
import com.coin.model.User;
import com.coin.web.service.EmailService;
import com.coin.web.service.RegistrationService;
import com.coin.web.service.impl.CustomUserDetailsService;

public class UserTest  extends AbstractDaoTest {

	@Autowired
	private RegistrationService registrationService;

	@Autowired
	private UserDao userDao;

	@Autowired
	private FlaggeduserDao flaggeduserDao;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	 
	@Autowired
	private EmailService emailService;

	private static String email="jeff123@test.com";
	private static String password="test123";
	private static String hash="4D28DCACB842E7B82B561731DDF9F204";
	
	@Ignore
	@Test
	public void userTests () {
		User user = userDao.findByEmail(email);
		
		String str = null;
		try {
			str = registrationService.getEmailRegistrationHash(user);
		} catch (Exception e) {
			e.printStackTrace();
		}  
		
		log.info("emailRegistrationConfirm  "+str);

		
		Map<String, Object> data = registrationService.getRegistrationConfirmData(user, str);
		Email emailObj = emailService.getEmailFromTemplate(Config.emailRegistrationConfirmTemplate, data);
		log.debug("emailObj: "+emailObj.getBodyHtml());

		//emailService.send(emailObj, user.getEmail(), Config.sendHtml);
		
		boolean stat = registrationService.isValidRegistrationHash(user, str);
		log.info("isValidRegistrationHash stat: "+stat);

	}
	
	@Ignore
	@Test
	public void testNewReg( ) {
		Message msg = registrationService.newRegistration (email, password);
		log.info("msg: "+msg.getStatus()+ " test: "+msg.getMsg() );
		
		if (msg.getStatus().equals("success")) {
			Object extra = null;
			if ( msg != null )  extra = msg.getExtra();
			User newUser = null;
			if(extra!=null) newUser = (User) extra;
			if (newUser!=null) log.info("new user "+newUser.getStatus());
		}
	}	
	
	@Ignore
	@Test
	public   void testForgotPassword( ) {
		Message msg = registrationService.forgotPassword (email);
		log.info("msg: "+msg.getStatus()+ " test: "+msg.getMsg() );
		System.out.print("msg: "+msg.getMsg());
	}	

	@Test
	public void getUserObj () {
		User user = userDao.getUserObj("jeff@test.com"); 
		log.info("user: "+ user );

		log.info("roles: "+user.getRoles());
		log.info("notificationtypes: "+user.getNotificationtypes());

	}	
	
	@Test
	public void login () {
		String uname = "jeff@test.com";
		UserDetails c = customUserDetailsService.loadUserByUsername(uname);

		log.info("c: "+c);
		log.info("roles: "+c.getAuthorities());
	}	
	
	
	@Test
	public void testFlageduser1() {
		String fname="Wilfred";
		String lname="EGGLETON";
		List<Flaggeduser> lst = flaggeduserDao.fuzzyName(fname, lname);
		log.info("lst: "+lst);
		for(Flaggeduser f: lst) log.info("u: "+f);
	}
	
	
	@Test
	public void testFlageduser() {
		String name="EGGLETON, Wilfred";
		List<Flaggeduser> lst = flaggeduserDao.fuzzyName(name);
		log.info("lst: "+lst);
		for(Flaggeduser f: lst) log.info("u: "+f);
	}
	 
	@Test
	public void testSpringEncoder() {
	    PasswordEncoder encoder = new Md5PasswordEncoder();
	    String hashedPass = encoder.encodePassword("test1234", null);
	    log.debug(hashedPass);
	    assertEquals("16d7a4fca7442dda3ad93c9a726597e4", hashedPass);
	}
	
}