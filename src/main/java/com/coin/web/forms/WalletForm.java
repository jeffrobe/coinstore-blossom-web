package com.coin.web.forms;

import javax.persistence.Column;
 
public class WalletForm {

    private String name;
    public String getName() {  return name; }
    public void setName(String v) { this.name = v; }

    private Long id;
    public void setId(Long v) { this.id=v; }
    public Long getId() { return this.id; }
    
    private Long userId;
    public void setUserId(Long v) { this.userId=v; }
    public Long getUserId() { return this.userId; }
     
    private Long typeId;
    public void setTypeId(Long v) {  this.typeId=v; }
    public Long getTypeId() { return this.typeId; }
       
    
}
