package com.coin.web.forms;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;
 
public class FundingsourceForm {

    private String name;
    public String getName() {  return name; }
    public void setName(String v) { this.name = v; }

    private Long id;
    public void setId(Long v) { this.id=v; }
    public Long getId() { return this.id; }
    
    private Long userId;
    public void setUserId(Long v) { this.userId=v; }
    public Long getUserId() { return this.userId; }
    
    @NumberFormat(style = Style.CURRENCY)
    private BigDecimal deposit1;
    public void setDeposit1(BigDecimal v) { this.deposit1=v; }
    public BigDecimal getDeposit1() { return this.deposit1; }
    
    @NumberFormat(style = Style.CURRENCY)
    private BigDecimal deposit2;
    public void setDeposit2(BigDecimal v) { this.deposit2=v; }
    public BigDecimal getDeposit2() { return this.deposit2; }
    
    private String type;
    public void setType(String v) {  this.type=v; }
    public String getType() { return this.type; }
    
    private String account;
    public void setAccount(String v) {  this.account=v; }
    public String getAccount() { return this.account; }
    
    private String institution;
    public void setInstitution(String v) {  this.institution=v; }
    public String getInstitution() { return this.institution; }

    @Size(min = 9, max = 9)
    private String routing;
    public void setRouting(String v) {  this.routing=v; }
    public String getRouting() { return this.routing; }
    
    private String state;
    public void setState(String v) {  this.state=v; }
    public String getState() { return this.state; }

    private String postalcode;
    public void setPostalcode(String v) {  this.postalcode=v; }
    public String getPostalcode() { return this.postalcode; }
    
    private String city;
    public void setCity(String v) {  this.city=v; }
    public String getCity() { return this.city; }
    
}
