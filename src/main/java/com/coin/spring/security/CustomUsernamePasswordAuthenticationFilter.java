package com.coin.spring.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private static final Logger log = Logger.getLogger(CustomUsernamePasswordAuthenticationFilter.class);

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authResult)   throws IOException, ServletException {
		super.successfulAuthentication(request, response, authResult);
		log.info("CustomUsernamePasswordAuthenticationFilter successfulAuthentication login==");
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed)   throws IOException, ServletException {

		super.unsuccessfulAuthentication(request, response, failed);
		log.info("CustomUsernamePasswordAuthenticationFilter unsuccessfulAuthentication ==failed login==");
	}
	
}