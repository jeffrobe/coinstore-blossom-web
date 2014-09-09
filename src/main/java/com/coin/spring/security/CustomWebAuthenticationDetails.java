package com.coin.spring.security;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class CustomWebAuthenticationDetails extends WebAuthenticationDetails {
	private static final Logger log = Logger.getLogger(CustomWebAuthenticationDetails.class);

    private final String token;

    public CustomWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        token = request.getParameter("token");
    }

    public String getToken() { return token; }
    
    @Override
    public String toString() {
		return "CustomWebAuthenticationDetails [token="+ token+"]"; 
    }
}