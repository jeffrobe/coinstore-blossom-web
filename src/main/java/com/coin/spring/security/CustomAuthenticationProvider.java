package com.coin.spring.security;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.coin.common.BaseConfig;
import com.coin.model.Message;
import com.coin.web.service.PropertiesService;
import com.coin.web.model.CustomUserDetail;
import com.coin.web.service.RegistrationService;
import com.coin.web.service.impl.CustomUserDetailsService;
  
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	private static final Logger log = Logger.getLogger(CustomAuthenticationProvider.class);

    @Autowired
    private CustomUserDetailsService userService;
 
    @Autowired
    private RegistrationService registrationService;
 
	@Autowired
	protected PropertiesService propertiesService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		log.info("authenticate");
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();
		String token=null;
		String ip = "";
				
		log.debug("dets: "+ authentication.getDetails());
		if (authentication.getDetails()!=null) {
			CustomWebAuthenticationDetails dets = (CustomWebAuthenticationDetails) authentication.getDetails();
			if ( propertiesService.getRequireTokenAuth() ) token = dets.getToken();
			ip=dets.getRemoteAddress();
		}
	
		log.debug("token: "+ token+ " username: "+username+ " password: " + password + " ip: "+ip);
        
		CustomUserDetail user = (CustomUserDetail) userService.loadUserByUsername(username);
		if (user==null) throw new BadCredentialsException("user not found");
		user.setIp(ip);
		Message msg=null;
		msg = registrationService.validateLogin (user, username, password); 
		if (msg.getStatus().equals(BaseConfig.MSG_ERROR)){
			log.info(msg.getMsg());
			registrationService.recordLogin(user,msg.getMsg());
			throw new BadCredentialsException(msg.getMsg());
		}
		
		if (propertiesService.getRequireTokenAuth()){
			msg = registrationService.validateToken(token); 
			if (msg.getStatus().equals(BaseConfig.MSG_ERROR)) {
				log.info(msg.getMsg());
				registrationService.recordLogin(user,msg.getMsg());
				throw new BadCredentialsException(msg.getMsg());			
			}
		} 
		
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        if(authorities!=null) log.info("authorities: "+authorities.toString());
        
        registrationService.recordLogin(user, "success");
        return new UsernamePasswordAuthenticationToken(user, password, authorities);
	}
 
	@Override
	public boolean supports(Class<?> arg0) {
        return true;
    }
}