package com.coin.service;
 
 
import java.util.List;
 



import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.coin.web.AbstractServiceTest;
import com.coin.dao.FundingsourceDao;
import com.coin.model.Fundingsource;
import com.coin.model.Message;
import com.coin.web.service.FundingsourceService;
import com.coin.service.PropertiesService;
 
 
public class FundingSourceServiceTest  extends AbstractServiceTest  {
	@Autowired
	private FundingsourceDao fundingsourceDao;
	
	@Autowired
	private FundingsourceService fundingsourceService;
	 
	private Long userId=1l;
	String routing = "011000015";
	String account = "123";

	@Test
	public void testMax()  {
		Message m = fundingsourceService.testMaxSource(userId);
		log.info(m);
	}

	@Test
	public void testDup()  {
		Message m = fundingsourceService.testDuplicateSource(userId, routing, account);
		log.info(m);
	}

	
	@Test
	public void updateSource()   {
		Fundingsource fundingsource = fundingsourceDao.findById(1l);
		fundingsource.setRouting("011000015");
		fundingsource.setAccount("123");
		fundingsource.setName("test");
		
		Message m = fundingsourceService.updateSource(fundingsource);
		log.info("m: "+m.getStatus()+ " m: "+m.getMsg() );
		log.info("f: "+m.getExtra());
    }
	
	
	@Test
	public void newSource()   {
		Fundingsource fundingsource = fundingsourceDao.findById(1l);
		fundingsource.setId(null);
		fundingsource.setName("new name");
		
		Message m = fundingsourceService.newSource(fundingsource);
		log.info("f: "+m.getExtra());
    }
   
}