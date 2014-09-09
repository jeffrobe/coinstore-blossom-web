package com.coin.web.service.impl;


import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.coin.model.BitCoinAddress;
import com.coin.model.BTCTicker;
import com.coin.web.service.BTCTickerService;
import com.coin.web.service.PropertiesService;
import com.xeiam.xchange.BaseExchange;
 
 
@Service ("btcTickerService")
public class BTCTickerServiceImpl extends BaseCacheServiceImpl implements BTCTickerService {
	@Autowired
	protected PropertiesService propertiesService;
	
	private List<BTCTicker> tickers;
 	private String baseRestTickersUrl;
 	private String baseRestTickerUrl;

	
	public BTCTickerServiceImpl () {
	 
	}
 
	
	@Override
	public List<BTCTicker> getTickers() {
		log.debug("gettickers");
 
		if ( tickers==null || this.isCacheExpired() ) loadTickers();
		 
		return this.tickers;
	}
	
	@Override
	public void loadTickers() {
		log.debug("loadTickers");
		
		baseRestTickersUrl = propertiesService.getBTCExchangeServerUrl()+propertiesService.getBTCTickersUrl();
	
		log.debug("url: "+baseRestTickersUrl);
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<BTCTicker[]> responseEntity = restTemplate.getForEntity(baseRestTickersUrl, BTCTicker[].class);
		
		log.debug("responseEntity: "+responseEntity);
		BTCTicker[] ticker = responseEntity.getBody();
		tickers=new LinkedList<BTCTicker> ();
		for (BTCTicker b  : ticker) {
			log.info("b:"+b);
			tickers.add(b);
		}
		
	    super.setCacheDate0( new Date() );  		
	}
 
	@Override
	public BTCTicker getTicker(String ident) {
		log.debug("loadExchange");
		
		baseRestTickerUrl = propertiesService.getBTCExchangeServerUrl()+propertiesService.getBTCTickerUrl();
		String url = baseRestTickerUrl + "/" + ident;
		log.debug("url: "+url);
		
		RestTemplate restTemplate = new RestTemplate();
		BTCTicker btc = restTemplate.getForObject(url, BTCTicker.class);
		log.debug("btc: "+btc);
		
		return btc;
	}
	
}
