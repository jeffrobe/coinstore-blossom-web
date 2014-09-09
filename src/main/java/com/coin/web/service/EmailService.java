package com.coin.web.service;

import java.util.Map;

import com.coin.model.Email;
 
 

public interface EmailService   {

	void send(Email email, String to, boolean sendHtml);

	Email getEmailFromTemplate(String template, Map<String, Object> model);
	
}