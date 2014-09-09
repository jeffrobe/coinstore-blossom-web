package com.coin.service;
 
 
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.coin.model.BitCoinAddress;
import com.coin.web.AbstractServiceTest;
import com.coin.web.model.BtcaddressEnhanced;
import com.coin.web.service.impl.BTCUserServiceImpl;
 
 
public class BtcUserTest  extends AbstractServiceTest  {

	@Autowired
	private BTCUserServiceImpl btcUserServiceImpl;
		
	@Test
	public void getTickers() {
		String address = "1B3XPn8hRLFzuF3q1o6vAsZnormP2sxed4";
		BitCoinAddress item = btcUserServiceImpl.getBtcaddress(address);
		log.info("item: "+item);
		
		Long userId =1l; 
		List<BtcaddressEnhanced> item1 = btcUserServiceImpl.getEnhancedBtcaddresses(userId);
		log.info("item: "+item1);
    }
   
}