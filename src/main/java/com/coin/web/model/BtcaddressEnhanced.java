package com.coin.web.model;

import java.io.Serializable;
import java.util.Date;
import com.coin.model.BitCoinAddress;
 
public class BtcaddressEnhanced implements Serializable {
	private static final long serialVersionUID = 1L;
	
	BtcaddressEnhanced() { }
	public BtcaddressEnhanced(String address) {
		this.updated= new Date();
		this.btcaddress=address;
	}


	private Long id;
	public void setId(Long v) { this.id=v; }
	public Long getId() { return this.id; }

	private Date updated;
	public void setUpdated(Date v) { this.updated=v; }
	public Date getUpdated() { return this.updated; }

	private String btcaddress;
	public void setBtcaddress(String v) { this.btcaddress=v; }
	public String getBtcaddress() { return this.btcaddress; }
	
	private BitCoinAddress bitcoinAddress;
	public void setBitcoinAddress(BitCoinAddress v) { this.bitcoinAddress=v; }
	public BitCoinAddress getBitcoinAddress() { return this.bitcoinAddress; }
	 
    
    @Override
	public String toString() {
		return "BtcaddressEnhanced [id=" + id + ", address=" + btcaddress + ", updated=" + updated+ ", bitcoinAddress=" + bitcoinAddress + "]";
	}

}
