package com.coin.web.dao;
 
 
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.coin.dao.BankDao;
import com.coin.model.Bank;
 
public class BankTest  extends AbstractDaoTest  {
	private static final Logger log = Logger.getLogger(BankTest.class);
 
	@Autowired
	private BankDao bankDao;
	 
	@Test
	public void getBanks()   {
		List<Bank> lst = bankDao.list();
		log.debug("msg: "+lst.toString());
		log.debug("banks: "+lst.toString());
		
		log.info("done");
    }
   
}