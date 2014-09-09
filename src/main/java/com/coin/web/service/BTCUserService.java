package com.coin.web.service;
 
import java.util.List;
import com.coin.model.BitCoinAddress;
import com.coin.web.model.BtcaddressEnhanced;

public interface BTCUserService  extends BaseCacheService {
 
	List<BtcaddressEnhanced> getEnhancedBtcaddresses(Long userId);

	BitCoinAddress getBtcaddress(String address);
}
