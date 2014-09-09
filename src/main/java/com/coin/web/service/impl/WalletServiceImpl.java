package com.coin.web.service.impl;


import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coin.common.BaseConfig;
import com.coin.dao.WalletDao;
import com.coin.model.Message;
import com.coin.model.Wallet;
import com.coin.web.service.WalletService;
 
 
@Service ("walletService")
public class WalletServiceImpl implements WalletService {
	private static final Logger log = Logger.getLogger(WalletService.class);
 
	@Autowired
	WalletDao walletDao;
	
	public WalletServiceImpl (  ) {
		
	}

	@Override
	public Message updateWallet(Wallet wallet) {
		Wallet walletOld = walletDao.findById(wallet.getId());
		if (!walletOld.getName().equals(wallet.getName())) {
			if ( walletDao.exists ( wallet.getName(), wallet.getUserId()) )
			return new  Message(0,BaseConfig.MSG_ERROR,"name_exist");
		}
  
		wallet.setStatus(BaseConfig.statusActive);
		walletDao.update (wallet);
		 

		return new Message(1,BaseConfig.MSG_SUCCESS, "created_fundingsource", (Object) wallet );
	}
	
	@Override
	public Message newWallet(Wallet wallet) {
		if ( walletDao.exists ( wallet.getName(), wallet.getUserId()) )
			return new  Message(0,BaseConfig.MSG_ERROR,"name_exist");
 
		wallet.setEntered(new Date());
		wallet.setStatus(BaseConfig.statusActive);
		Wallet walletNew = walletDao.saveFlush(wallet);
		log.debug("new id: "+walletNew.getId());
		 
		
		return new Message(1,BaseConfig.MSG_SUCCESS, "created_fundingsource", (Object) walletNew );
	}
	
	  
	
}
