package com.coin.web.dao;
 
import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
 
import com.coin.dao.FundingsourceDao;
import com.coin.model.Fundingsource;
import com.coin.model.Message;
import com.coin.web.service.FundingsourceService;

import org.apache.commons.validator.routines.checkdigit.ABANumberCheckDigit;
 
public class FundingsourceTest extends AbstractDaoTest {
	@Autowired
	private FundingsourceService fundingsourceService;

	@Autowired
	private FundingsourceDao fundingsourceDao;
 

	private static Long userId=1l;
	private static Long fundingsourceId=1l;
	private static Long trialDepositId=3l;
	
	@Transactional
	public void save_succeeds() {
	        
	}
   
	@Ignore
	@Test
	public void addFundingSource() throws Exception {
		
		log.info("addFundingSource");
		String name="citi savings 12312";
		String type="savings";
		String account="23123";
		String routing="1234";
		
		Fundingsource f = new Fundingsource(userId, "", name, type, account, routing);
		Message msg = fundingsourceService.newSource(f);
		log.info("msg: "+msg);
	}
	
	@Ignore
	@Test
	public void createTrialDepost() throws Exception {
		Message msg = fundingsourceService.createTrialDeposit(fundingsourceId);
		log.info("msg: "+msg);
	}
	
	@Ignore
	@Test
	public void confirmTrialDepost() throws Exception {
		BigDecimal deposit1= new BigDecimal(0.11);
		BigDecimal deposit2=new BigDecimal(0.12);
		
		Message msg = fundingsourceService.confirmTrialDeposit(trialDepositId, deposit1, deposit2);
		log.info("msg: "+msg);
	}
	
	@Test
	public void validateRouting() throws Exception {
		String routingBad = "123456789";
		String routingGood = "082901473";
		
		ABANumberCheckDigit ab = new ABANumberCheckDigit();
 
		
		log.info("routing good: "+ routingGood+ " stat "+ab.isValid(routingGood));
		log.info("routing bad: "+ routingBad+ " stat "+ab.isValid(routingBad));
	}
	
}