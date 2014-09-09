package com.coin.web.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

public interface IAuthenticationFacade  {
    Authentication getAuthentication();

	Long getUserId();
	String getUserDisplayName();
	String getUserName();

	Boolean isLoggedIn();
}

 