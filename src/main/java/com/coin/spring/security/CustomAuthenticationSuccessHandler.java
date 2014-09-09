package com.coin.spring.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;


public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	private static final Logger log = Logger.getLogger(CustomAuthenticationSuccessHandler.class);

	@Override
	protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {
		log.info("determineTargetUrl");
		return request.getServletPath();
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
		log.info("onAuthenticationSuccess");

	    request.getRequestDispatcher(request.getServletPath()).forward(request, response);
	}
}