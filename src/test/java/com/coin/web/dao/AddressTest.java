package com.coin.web.dao;
 
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.coin.dao.FundingsourceDao;
import com.coin.dao.BtcaddressDao;
import com.coin.dao.WalletDao;
import com.coin.model.Wallet;
 
import com.coin.model.Btcaddress;
 
 


import org.junit.Ignore;
import org.junit.Test;

public class AddressTest extends AbstractDaoTest {
 	
	@Autowired
	private WalletDao walletDao;

	@Autowired
	private BtcaddressDao btcaddressDao;


	private static Long userId=1l; 
 
	@Test
	public void list() throws Exception {
		
		log.info("listWallet");

		List<Btcaddress> lst = btcaddressDao.list();
		log.info("lst: "+lst);
	}
	 
	
	@Test
	public void listuser() throws Exception {
		
		log.info("listWallet");

		List<Btcaddress> lst = btcaddressDao.listObjByUserId(userId);
		for (Btcaddress btcaddress :  lst ) {
    		log.info ( "walet: " + btcaddress.getWallet().getName());
    	}
		
		log.info("lst: "+lst);
	}
	
}