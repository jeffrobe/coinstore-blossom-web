package com.coin.service;
 
 
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.coin.web.AbstractServiceTest;
import com.coin.common.BaseConfig;
import com.coin.dao.LoginDao;
import com.coin.dao.UserDao;
import com.coin.model.User;
import com.coin.model.Message;
import com.coin.web.model.CustomUserDetail;
import com.coin.web.service.RegistrationService;
  
 
public class LoginServiceTest  extends AbstractServiceTest  {
	@Autowired
	private LoginDao loginDao;
	
	@Autowired
	private RegistrationService registrationService;
	 
	private Long userId=1l;
	 
	 
	@Test
	public void testRecord()  {
		CustomUserDetail customUserDetail = new CustomUserDetail();
		customUserDetail.setId(userId);
		customUserDetail.setIp("1234");
		String code="success";
		Message m = registrationService.recordLogin (customUserDetail,  code);
		log.info(m);
	}
	
	@Test
	public void testValidate()  {
		CustomUserDetail customUserDetail = new CustomUserDetail();
		customUserDetail.setId(userId);
		customUserDetail.setIp("1234");
		customUserDetail.setPassword("16d7a4fca7442dda3ad93c9a726597e4");
		customUserDetail.setToken("123");
		customUserDetail.setStatus(BaseConfig.statusActive);
		String email="test123@test.com";
		String password="test1234";
		
		Message m = registrationService.validateLogin (customUserDetail, email, password);
		log.info(m);
	}
 
	
	
   
}