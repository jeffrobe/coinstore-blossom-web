package com.coin.web.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
 
import java.util.Map;
 


import com.coin.web.model.CustomUserDetail;
import com.coin.model.Message;
import com.coin.model.User;
 
public interface RegistrationService {


	Message getUserFromHash( String email, String hash);

	Message confirmNewUserTos(Long userId);

	Message confirmUserTos( String email);

	Message forgotPassword(String email);

	Message newRegistration(String email, String password);
	
	String getEmailRegistrationHash(User user) throws NoSuchAlgorithmException,
			UnsupportedEncodingException;

	String getEncodedPassword(String str);

	Map<String, Object> getRegistrationConfirmData(User user, String hash);

	Boolean isValidRegistrationHash(User user, String str);

	Message autoLogin(Long userId);

	Message getRegisteredUser(String email);

	Message validateToken(String token);

	Message validateLogin(CustomUserDetail user, String email, String password);

	Message newUserActive(Long userId);

	Message resetPassword(Long userId, String password);

	Map<String, Object> getForgotPasswordData(User user, String hash);

	Message updateProfilePhone(Long userId, String phone);

	Message updateProfile(User user);
 
 
	Message recordLogin(CustomUserDetail customUserDetail, String code);
	
	 
 
}
