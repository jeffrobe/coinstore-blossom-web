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
import com.coin.dao.WallettypeDao;
import com.coin.dao.WalletDao;
import com.coin.model.Wallet;
import com.coin.model.Fundingsource;
import com.coin.model.Message;
import com.coin.model.Wallettype;
import com.coin.web.service.WalletService;

import org.junit.Ignore;
import org.junit.Test;

public class WalletTest extends AbstractDaoTest {
	@Autowired
	private WalletService walletService;

	@Autowired
	private FundingsourceDao fundingsourceDao;
	
	@Autowired
	private WalletDao walletDao;

	@Autowired
	private WallettypeDao wallettypeDao;

	private static final Logger log = Logger.getLogger(WalletTest.class);

	private static Long userId=1l; 
 
	@Test
	public void listWallet() throws Exception {
		
		log.info("listWallet");

		List<Wallet> lst = walletDao.list();
		log.info("lst: "+lst);
	}
	
	@Test
	public void addWallet() throws Exception {
		
		log.info("addFundingSource");
		String name="citi savings 12312";
		String type="savings";
		
		Wallet f = new Wallet(userId, name);
		Message msg = walletService.newWallet(f);
		log.info("msg: "+msg);
	}
	  
	@Test
	public void getActiveTypes() throws Exception {

		Map<Long,String> mp = new LinkedHashMap<Long,String>();
		
		List<Wallettype> wts = wallettypeDao.list();
		for (Wallettype w: wts) {
			log.debug("naem: "+ w.getName() );
			mp.put(w.getId(), w.getName());
		}
	
		log.debug("mp: "+  mp );
	}

	@Test
	public void listWalletObj() throws Exception {

		Map<Long,String> mp = new LinkedHashMap<Long,String>();
		
		List<Wallet> wts = walletDao.listActiveObjByUserId(userId);
		for (Wallet w: wts) {
			log.debug("naem: "+ w.getName() + " type " + w.getWallettype().getName() );
		}
	
	}

	@Test
	public void getWalletObj() throws Exception {

		 
		Wallet w = walletDao.getObj(9l);
		log.debug("naem: "+ w.getName() + " type " + w.getWallettype().getName() );
			
	}
	
}