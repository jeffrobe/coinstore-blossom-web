package com.coin.web.forms;

import javax.persistence.Column;
 
public class BtcaddressForm {

    private String name;
    public String getName() {  return name; }
    public void setName(String v) { this.name = v; }

    private Long id;
    public void setId(Long v) { this.id=v; }
    public Long getId() { return this.id; }
    
    private Long userId;
    public void setUserId(Long v) { this.userId=v; }
    public Long getUserId() { return this.userId; }
     
    private Long walletId;
    public void setWalletId(Long v) {  this.walletId=v; }
    public Long getWalletId() { return this.walletId; }

    private String privatekey;
    public void setPrivatekey(String v) { this.privatekey=v; }
    public String getPrivatekey() { return this.privatekey; }
       
    private String publickey;
    public void setPublickey(String v) { this.publickey=v; }
    public String getPublickey() { return this.publickey; }
    
}
