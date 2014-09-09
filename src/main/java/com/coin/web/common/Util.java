package com.coin.web.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.coin.web.service.PropertiesService;
import com.coin.web.model.CustomUserDetail;
import com.coin.common.BaseUtil;
import com.coin.model.LongSelOption;
import com.coin.model.User;
 
import com.coin.web.common.Config;

import edu.vt.middleware.password.CharacterCharacteristicsRule;
import edu.vt.middleware.password.LengthRule;
import edu.vt.middleware.password.Rule;
import edu.vt.middleware.password.WhitespaceRule;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

public class Util extends BaseUtil {

	public static String getEmailTo(String email, PropertiesService propertiesService) {
		if (propertiesService.overrideEmail() ) email=propertiesService.getEmailOverrideAddress();
		return email;
		
	}
	
    public static List<LongSelOption> getOptionsList ( Map<Long,String> items ) { 
    	List<LongSelOption> m = new LinkedList<LongSelOption>();
    	if (Config.SelectOptionDefaultEntry) m.add( new LongSelOption( null, Config.SelectOptionDefaultLabel) );
    	 
    	for(Long key : items.keySet())
    		m.add( new LongSelOption( key, items.get(key)) );

    	return m;
    }
    
				
	public static List<Rule> getPasswordRules (){
		// password must be between 8 and 16 chars long
		LengthRule lengthRule = new LengthRule(8, 16);

		// don't allow whitespace
		WhitespaceRule whitespaceRule = new WhitespaceRule();

		// control allowed characters
		CharacterCharacteristicsRule charRule = new CharacterCharacteristicsRule();

		// group all rules together in a List
		List<Rule> ruleList = new ArrayList<Rule>();
		ruleList.add(lengthRule);
		ruleList.add(whitespaceRule);
		
		return ruleList;
	}
	
	


	
	public static Long getLoggedUserId (Authentication auth){
		 
	    if (auth==null) return null;
		Object princ = auth.getPrincipal();
	    if (princ==null) return null;
		if (princ instanceof CustomUserDetail) {
			CustomUserDetail user = (CustomUserDetail) princ;    
		    log.info("customUserDetail id: "+user.getId()+ " getUsername: "+user.getUsername() );    
		    return user.getId();
		}
		
		return null;
	}
	
	public static Long getLoggedUserId (){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth==null) return null;
		Object princ = auth.getPrincipal();
	    if (princ==null) return null;
		if (princ instanceof CustomUserDetail) {
			CustomUserDetail user = (CustomUserDetail) princ;    
		    log.info("customUserDetail id: "+user.getId()+ " getUsername: "+user.getUsername() );    
		    return user.getId();
		}
		
		return null;
	}
	
	public static boolean isPhoneNumberValid(String phoneNumber) {
		
		String regex = "^\\+?[0-9. ()-]{10,25}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(phoneNumber);
		  
		if(matcher.matches()) return true;
	    return false;
	}
	
    public static Long getSessionUserId (HttpServletRequest request) {
    	Long userId = null;
    	try {
    		userId = (Long) request.getSession().getAttribute("userId");
    	} catch (Exception e) {
    		log.error("unable tot get user id"+e.getMessage());
    	}
    	return userId;
    }
	
    public static void setSessionLogin (HttpServletRequest request, User user) {

    	request.getSession().setAttribute("userId",user.getId());
  		request.getSession().setAttribute("userStatus",user.getStatus());
    	 
    }
	
    public static void setSessionLogin (HttpServletRequest request, Long userId) {

    	request.getSession().setAttribute("userId",userId);
    	 
    }
    
	public static boolean validateEmail(String email) {
	    	 
		if (email == null) return false; // email is empty
	 
		Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
		// Match the given string with the pattern
		Matcher m = p.matcher(email);
	 
		// check whether match is found
		boolean matchFound = m.matches();
	 
		StringTokenizer st = new StringTokenizer(email, ".");
		String lastToken = null;
		while (st.hasMoreTokens()) {
			lastToken = st.nextToken();
		}
	 
		return matchFound && lastToken.length() >= 2 && email.length() - 1 != lastToken.length();
	}
	
	
	public static boolean isValidRecaptcha ( ReCaptchaImpl reCaptcha, String remoteAddr, String challangeField, String responseField) {
	    	ReCaptchaResponse reCaptchaResponse = null;
	    	try {
	        	
				reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, challangeField, responseField);
	    	} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
	    	
			System.out.print("challangeField:"+reCaptchaResponse);
			
			if ( !reCaptchaResponse.isValid() )  {
				//ObjectError e = new ObjectError("captcha error", reCaptchaResponse.getErrorMessage());
				//result.addError(e);
				
				return false;
			}
			return true;
	    }

	 
}