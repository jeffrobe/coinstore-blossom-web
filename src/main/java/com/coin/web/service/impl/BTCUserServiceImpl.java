package com.coin.web.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.coin.dao.BtcaddressDao;
import com.coin.model.Btcaddress;
import com.coin.model.BitCoinAddress;
import com.coin.web.model.BtcaddressEnhanced;
import com.coin.web.service.BTCUserService;
import com.coin.web.service.PropertiesService;

 
@Service ("btcUserService")
public class BTCUserServiceImpl extends BaseCacheServiceImpl implements BTCUserService {
	@Autowired
	private BtcaddressDao btcaddressDao;
	
	@Autowired
	protected PropertiesService propertiesService;
	
 	private String baseRestUrl;
 	
	public BTCUserServiceImpl () {
	 
		 
	}

	@Override
	public BitCoinAddress getBtcaddress (String address) {
		log.debug("getEnhancedBtcaddress: "+address); 
		
		baseRestUrl = propertiesService.getBTCExchangeServerUrl()+propertiesService.getBTCAddressUrl();
		String url = baseRestUrl + "/" + address;
		log.debug("url: "+url);
		
		RestTemplate restTemplate = new RestTemplate();
		BitCoinAddress btc = restTemplate.getForObject(url, BitCoinAddress.class);
		log.debug("btc: "+btc);
		
		return btc;
	}	
	
	@Override
	public List<BtcaddressEnhanced> getEnhancedBtcaddresses (Long userId) {
   	 	log.info("getEnhancedBtcaddresses fom uservice userId: "+userId);
   	 	List<BtcaddressEnhanced> lstFull = null;
   	 
   	 	List<Btcaddress> btcs = btcaddressDao.findByActiveUserId(userId);
   	 	if (btcs != null && btcs.size()>0) lstFull=new LinkedList<BtcaddressEnhanced>();
   	 	for (Btcaddress b : btcs) {
   	 		String address = b.getPublickey();
   	 		
   	 		BtcaddressEnhanced btcEnhanced = new BtcaddressEnhanced(address);
   	 		BitCoinAddress feed = getBtcaddress (address);
   	 		btcEnhanced.setId(b.getId());
	 		btcEnhanced.setBitcoinAddress(feed);
   	 		lstFull.add(btcEnhanced);
   	 	}
   	 	
    	return lstFull;
	}	
	

	
}
