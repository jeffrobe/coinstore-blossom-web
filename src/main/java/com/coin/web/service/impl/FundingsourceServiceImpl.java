package com.coin.web.service.impl;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coin.common.BaseConfig;
import com.coin.dao.BankDao;
import com.coin.dao.FundingsourceDao;
import com.coin.dao.TrialdepositDao;
import com.coin.model.Bank;
import com.coin.model.Fundingsource;
import com.coin.model.Message;
import com.coin.model.Trialdeposit;
import com.coin.web.service.PropertiesService;
import com.coin.web.service.BankService;
import com.coin.web.service.FundingsourceService;
 
 
@Service ("fundingsourceService")
public class FundingsourceServiceImpl implements FundingsourceService {
	private static final Logger log = Logger.getLogger(FundingsourceService.class);
 
	@Autowired
	FundingsourceDao fundingsourceDao;
	
	@Autowired
	BankService bankService;
	
	@Autowired
	TrialdepositDao trialdepositDao;
	
	@Autowired
	BankDao bankDao;
	
	@Autowired
	private PropertiesService propertiesService;
	
	public FundingsourceServiceImpl (  ) {
		
	}

	@Override
	public Message validatefundingSource(Fundingsource fundingsource) {
		Fundingsource fundingsourceOld = null;
		if(fundingsource.getId()!=null && fundingsource.getId()>0)
			fundingsourceOld = fundingsourceDao.findById(fundingsource.getId());
		
		if (fundingsourceOld==null || !fundingsourceOld.getName().equals(fundingsource.getName())) {
			if ( fundingsourceDao.exists ( fundingsource.getName(), fundingsource.getUserId()) )
			return new  Message(0,BaseConfig.MSG_ERROR,"name_exist");
		}
 
		if ( !bankService.isValidRouting(fundingsource.getRouting()) )
			return new  Message(0,BaseConfig.MSG_ERROR,"bad_routing");
	
		Message m = testDuplicateSource(fundingsource.getUserId(), fundingsource.getRouting(), fundingsource.getAccount());
		if (m!=null) return m; 	
		
		m=null;
		m = testMaxSource(fundingsource.getUserId());
		if (m!=null) return m; 	
		
		Bank bank = bankDao.findByRouting(fundingsource.getRouting());
		fundingsource.setInstitution(bank.getName());
		Message msg = new Message(0,BaseConfig.MSG_SUCCESS,"success");
		msg.setExtra(fundingsource);
		return msg;
	}
	

	@Override
	public Message testMaxSource(Long userId) {
		if (fundingsourceDao.getCount()>=propertiesService.getMaxFundingSourceSystem())
			return new Message(0,BaseConfig.MSG_ERROR,"max_fundingsource_system");			
	
		
		List<Fundingsource> lst = fundingsourceDao.findByUserId (userId);
		if (lst != null && lst.size()>=propertiesService.getMaxFundingSourcePerUser())
			return new Message(0,BaseConfig.MSG_ERROR,"max_fundingsource_user");			
	
		lst=null;
		lst = fundingsourceDao.findByActiveUserId(userId);
		log.debug("lst: "+lst.size());
		if (lst != null && lst.size()>=propertiesService.getMaxFundingSourceActivePerUser())
			return new Message(0,BaseConfig.MSG_ERROR,"max_active_fundingsource_user");			
	
		return null;
	}
	
	
	@Override
	public Message testDuplicateSource(Long userId, String routing, String account) {
		Fundingsource f = fundingsourceDao.findBy(userId, routing, account);
		log.debug("r:"+routing + " a:" + account + " f: "+f);
		Long delDate = new Date().getTime()-propertiesService.getFundingSourceDupUserMinutes()*1000*60;
		
		log.debug("delrec: "+ f.getUpdated().getTime()  + " delDate: " + delDate);
		
		if ( f != null ) {
			if (!f.getStatus().equals("deleted") || f.getUpdated().getTime() >= delDate ) return new Message(0,BaseConfig.MSG_ERROR,"account_inuse_user");			
		}
		
		f=null;
		f = fundingsourceDao.findBy(routing, account);
		 
		delDate=null;
		delDate = new Date().getTime()-propertiesService.getFundingSourceDupSystemMinutes()*1000*60;
		if ( f != null ) {
			if (!f.getStatus().equals("deleted") || f.getUpdated().getTime() >= delDate ) return new Message(0,BaseConfig.MSG_ERROR,"account_inuse_system");			
		}
		
		
		return null;

	}
	
	@Override
	public Message updateSource(Fundingsource fundingsource) {
		Message msg = validatefundingSource(fundingsource);
		if (msg.getStatus().equals(BaseConfig.MSG_ERROR)) return msg;
		
		fundingsource = (Fundingsource)msg.getExtra();
		
		fundingsource.setStatus(BaseConfig.statusPending);
 		fundingsourceDao.update (fundingsource);
		 
		return new Message(1,BaseConfig.MSG_SUCCESS, "created_fundingsource", (Object) fundingsource );
	}
	
	@Override
	public Message newSource(Fundingsource fundingsource) {
		Message msg = validatefundingSource(fundingsource);
		if (msg.getStatus().equals(BaseConfig.MSG_ERROR)) return msg;
		
		fundingsource = (Fundingsource)msg.getExtra();
		fundingsource.setStatus(BaseConfig.statusPending);
		fundingsource.setEntered(new Date());
		Fundingsource fundingsourceNew = fundingsourceDao.saveFlush(fundingsource);
		 
		fundingsource.setId(fundingsourceNew.getId());
		
		return new Message(1,BaseConfig.MSG_SUCCESS, "created_fundingsource", (Object) fundingsource );
	}
	
	
	@Override
	public Message createTrialDeposit(Long fundingsourceId) {
		Integer randomNum =  (int)(Math.random()*100); 
		Float deposit1= new Float(randomNum)/100;
		Integer randomNum2 =  (int)(Math.random()*100); 
		Float deposit2=new Float(randomNum2)/100;
		log.debug("deposit1: "+deposit1+ " deposit2: "+ deposit2);
		
		Trialdeposit t1 = new Trialdeposit( fundingsourceId, BaseConfig.statusActive, new Date(), new BigDecimal(deposit1), new BigDecimal(deposit2)); 
		trialdepositDao.save(t1);
 
		return  new Message(1,BaseConfig.MSG_SUCCESS, "created_deposit");

	}

	@Override
	public Message confirmTrialDeposit(Long trialdepositId, BigDecimal deposit1, BigDecimal deposit2) {
		Trialdeposit t = trialdepositDao.findById(trialdepositId);
		if (t==null) return new Message(0,BaseConfig.MSG_ERROR, "null");
		
		log.debug("in1: " + deposit1+ " t1: "+ t.getDeposit1()+ " in2: " + deposit2+ " t2: "+ t.getDeposit2());
		if ( deposit1==null || !deposit1.equals(t.getDeposit1()) ) return new Message(0,BaseConfig.MSG_ERROR, "invalid_deposit1");
		if ( deposit2==null || !deposit2.equals(t.getDeposit2()) ) return new Message(0,BaseConfig.MSG_ERROR, "invalid_deposit2");

		t.setConfirmed(new Date());
		t.setStatus(BaseConfig.statusActive);
		trialdepositDao.update(t);
 
		
		Fundingsource f = fundingsourceDao.findById(t.getFundingsourceId());
		f.setStatus(BaseConfig.statusActive);
		f.setConfirmed(new Date());
		fundingsourceDao.update(f);
		
		return  new Message(1,BaseConfig.MSG_SUCCESS, "confirmed_deposit");

	}
	
}
