package com.coin.web.service;
 
import java.util.List;

import com.coin.model.BTCExchange;
import com.coin.model.BTCTicker;

public interface BTCTickerService  extends BaseCacheService {
	void loadTickers();
	List<BTCTicker> getTickers();
	BTCTicker getTicker(String ident);
}
