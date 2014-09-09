package com.coin.web.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.coin.common.BaseConfig;
import com.coin.model.Message;
import com.coin.model.Role;
import com.coin.model.User;
import com.coin.web.model.CustomUserDetail;
import com.coin.web.service.RegistrationService;
 

@Service ("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
	private static final Logger log = Logger.getLogger(CustomUserDetailsService.class);

	@Autowired
	RegistrationService registrationService;

	public CustomUserDetailsService () {
		log.info("CustomUserDetailsService");
		 
	}

	@Override
	public UserDetails loadUserByUsername(String username)	throws UsernameNotFoundException {
		log.info("loadUserByUsername: "+username);
		Message msg = registrationService.getRegisteredUser(username);
		if (msg.getStatus().equals(BaseConfig.MSG_ERROR)) {
			log.info("bad or missing user");
			return null;
		}
			
		User user = (User) msg.getExtra();
		CustomUserDetail c = new CustomUserDetail();
		 
		log.info("set user:");
		c.setUsername(username);
		c.setPassword(user.getPassword());
		c.setId(user.getId());
		c.setStatus(user.getStatus());
		c.setRoles(getRoles(user.getRoles()));
		c.setDisplayName(user.getDisplayname());
		
		log.info("CustomUserDetail: "+c.toString());
		return c;
	}

 
	public List<String> getRoles( Set<Role> roles ) {
 		List<String> rolesStr = new ArrayList<String>();
 		for (Role r: roles) rolesStr.add(r.getIdent());
		return rolesStr;
	}


	 
}
