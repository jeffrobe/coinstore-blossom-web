package com.coin.service;
 
 
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
 

import com.coin.model.BTCExchange;
import com.coin.model.BTCTicker;
import com.coin.web.AbstractServiceTest;
import com.coin.web.service.impl.BTCTickerServiceImpl;
 
 
public class TickerServiceTest  extends AbstractServiceTest  {

	@Autowired
	private BTCTickerServiceImpl btcTickerService;

	@Test
	public void getTicker()   {
		BTCTicker item = btcTickerService.getTicker("average");
		log.info("item: "+item);
    }
	
	@Test
	public void getTickers()   {
		List<BTCTicker> lst = btcTickerService.getTickers();
		log.info("lst: "+lst);
    }
   
}