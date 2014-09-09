package com.coin.web.forms;
 

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

 
public class OrderForm {
    private String submitaction;
    public void setSubmitaction(String v) { this.submitaction=v; }
    public String getSubmitaction() { return this.submitaction; }
    
    private String mode;
    public void setMode(String v) { this.mode=v; }
    public String getMode() { return this.mode; }

    
    private Long id;
    public void setId(Long v) { this.id=v; }
    public Long getId() { return this.id; }
    
    private Long userId;
    public void setUserId(Long v) { this.userId=v; }
    public Long getUserId() { return this.userId; }
    
    private Long walletId;
    public void setWalletId(Long v) { this.walletId=v; }
    public Long getWalletId() { return this.walletId; }
    
    private Long fundingsourceId;
    public void setFundingsourceId(Long v) { this.fundingsourceId=v; }
    public Long getFundingsourceId() { return this.fundingsourceId; }
    
    private String type;
    public void setType(String v) { this.type=v; }
    public String getType() { return this.type; }
    
    
    @NumberFormat(style = Style.CURRENCY)
    private Float amount;
    public void setAmount(Float v) { this.amount=v; }
    public Float getAmount() { return this.amount; }
       
}
