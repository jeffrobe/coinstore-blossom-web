package com.coin.web.service;
 
import com.coin.model.Wallet;
import com.coin.model.Message;
 
public interface WalletService {
	Message newWallet(Wallet wallet);
	Message updateWallet(Wallet wallet);
	
	 
 
}
