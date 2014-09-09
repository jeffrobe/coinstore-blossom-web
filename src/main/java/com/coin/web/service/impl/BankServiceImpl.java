package com.coin.web.service.impl;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coin.common.BaseConfig;
import com.coin.dao.BankDao;
import com.coin.model.Bank;
import com.coin.web.service.BankService;
 
@Service ("bankService")
public class BankServiceImpl implements BankService {
	private static final Logger log = Logger.getLogger(BankService.class);
 
	@Autowired
	BankDao bankDao;

	public BankServiceImpl () {
		 
	}

	@Override
	public boolean isValidRouting(String routing) {
		Bank bank = bankDao.findByRouting(routing);
		if (bank==null) return false;
		String status = bank.getStatus();
		if (!status.equals(BaseConfig.statusActive)) return false;
		return true;
	}
	 
}
