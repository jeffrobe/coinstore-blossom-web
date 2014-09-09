package com.coin.web.service.impl;


import java.io.IOException;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coin.common.BaseConfig;
 
import com.coin.web.common.Util;
import com.coin.web.service.BaseCacheService;
//test
public class BaseCacheServiceImpl implements BaseCacheService {
	protected static final Logger log = Logger.getLogger(BaseCacheService.class);
	
	 
	public BaseCacheServiceImpl () {		
	}

	@Override
	public boolean isCacheExpired() {
		log.debug("isCacheExpired timeout: "+ cacheTimeOutSec+ " dateo: "+cacheDate0);
 		if (Util.isCacheTimeOutExpired(cacheDate0, cacheTimeOutSec) ) return true;
		return false;
	}

	protected Integer cacheTimeOutSec;
	protected void setCacheTimeOutSec (Integer v) { this.cacheTimeOutSec = v; } 
	protected Integer getCacheTimeOutSec () { return this.cacheTimeOutSec; } 
	
	protected Date cacheDate0;
	protected void setCacheDate0 (Date v) { this.cacheDate0 = v; } 
	protected Date getCacheDate0 () { return this.cacheDate0; } 
}
