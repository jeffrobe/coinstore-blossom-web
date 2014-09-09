package com.coin.web;
 
 
import java.util.Map;

import org.apache.log4j.Logger;
 
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
 
 

import com.authy.*;
import com.authy.api.*;
import com.coin.common.BaseConfig;

public class Authy {
	private static final Logger log = Logger.getLogger(Authy.class);

	private static ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	
 
	private static String email="test@yahoo.com";
	private static Integer authyid=2515356;
	 
	public static void main( String[] args ) {
		AuthyApiClient client = null; // new AuthyApiClient(Config.authyKey, Config.authyUrl);
		
		String tokenEntered="123";
		System.out.println("tokenEntered: "+tokenEntered);
		//Users users = client.getUsers();
		Tokens tokens = client.getTokens();
		System.out.println("tokens: "+ tokens.toString());
		Token verification = tokens.verify(authyid, tokenEntered);
		System.out.println("done: "+ verification.toString());
		//User user = users.createUser("new_user@email.com", "405-342-5699", "1");
		
		//Token verification = tokens.verify(authy_id, tokenEntered);
		
		System.out.println("done");
    }

	 
}