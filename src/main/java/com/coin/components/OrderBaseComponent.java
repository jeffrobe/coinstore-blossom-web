package com.coin.components;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.coin.web.common.Config;
import com.coin.web.common.Util;
import com.coin.dao.FundingsourceDao;
import com.coin.dao.OrderDao;
import com.coin.dao.WalletDao;
import com.coin.web.forms.OrderForm;
import com.coin.web.forms.OrderFormValidator;
import com.coin.model.Fundingsource;
import com.coin.model.LongSelOption;
import com.coin.model.Message;
import com.coin.model.Order;
import com.coin.model.Wallet;
import com.coin.web.service.IAuthenticationFacade;
import com.coin.web.service.OrderService;

public class OrderBaseComponent extends BaseComponent {
 
	@Autowired
	protected OrderDao orderhDao;

	@Autowired
	protected WalletDao walletDao;

	@Autowired
	protected FundingsourceDao fundingsourceDao;

	@Autowired
	protected OrderService orderService;
	 
	@Autowired
	private IAuthenticationFacade authenticationFacade;
	 
	public String viewForm( ModelMap model, 
    		@RequestParam (value = "id", required = false) Long id, 
    		HttpServletRequest request, @ModelAttribute OrderForm orderForm)
    {
	 
        if(id!=null && id >0) {
        	Order orderh = orderhDao.findById(id);
        	 
        	orderForm.setId(orderh.getId());
        	orderForm.setWalletId(orderh.getWalletId());
        	orderForm.setFundingsourceId(orderh.getFundingsourceId());
        	
        	orderForm.setAmount(orderh.getAmount());	
        }
        
        orderForm.setMode("form");
        return "form"; 
    }

	 
    public String handleSubmit(String orderType, ModelMap model, @ModelAttribute OrderForm orderForm,
    		BindingResult result, Node content,  HttpServletRequest request
    		) throws RepositoryException {
 
    	Long orderId = null;
    	String mode = orderForm.getMode();

    	String action = null;
    	if (orderForm.getSubmitaction()!=null) action = orderForm.getSubmitaction().toLowerCase();
    	log.debug("handleSubmit  mode: "+mode+ " action: " + action);
    	
        Message msg = null;
        Order orderh = null;
        
        if (mode.equals("form")) {
        	String retStr = handleFormOptions(orderType, result, model, orderForm, orderh);
        	if (retStr!=null) return retStr;
        }
        else {
        	String retStr = handleConfirmOptions(action, orderForm);
        	if (retStr!=null) return retStr;
        }
        
		return "confirm";
    }
    
    private Order calcFees (Order order) {
    	log.debug("calc fees");
  		Float amount = 0f;
  	 
      	if(order.getAmount()!=null) amount = order.getAmount();
      	Float coinValue = amount * getFxBuy();
      	
      	Float feeBase= getTransactionBaseFee();
      	Float feePct = amount*getTransactionPctFee();
      	Float feeTotal= feeBase+feePct;
      	Float totalCost = amount + feeTotal;
      	
      
      	order.setFeebase(feeBase);
      	order.setFeepct(feePct); 
      	order.setFeetotal(feeTotal);
      	
      	order.setCoinvalue(coinValue);
      	order.setFxrate(getFxBuy());
      	order.setTotalcost(totalCost);
      	    	
  		return order;
    }
    
    private String handleConfirmOptions(String action, OrderForm orderForm) {
    	Long orderId = orderForm.getId();
    	log.debug("handle confirm action: "+action+" order id: "+orderId);
        Message msg;
        Long userId = authenticationFacade.getUserId();
        
		if (action.contains("confirm")) {
	    	log.debug("confirm order");
			msg = orderService.confirmOrder(userId, orderId);
			log.debug("msg:"+msg);
	    	return "success";
	    }	
	    
	    if (action.contains("change")) {
	    	log.debug("change order");
	    	 
	    	return "formredir";
	    }	
	    if (action.contains("cancel")) {
	    	log.debug("cancel order");
	    	msg = orderService.cancelOrder(userId, orderId);
			log.debug("msg:"+msg);		 
	    	return "success";
	    }	
	    
	    return "success";
    }
    
    private String handleFormOptions(String orderType, BindingResult result, ModelMap model, OrderForm orderForm, Order order) {
      	log.debug("handleFormOptions mode form:");
    	Long orderId;
    	Message msg;
    	Long userId = authenticationFacade.getUserId();
    	 
    	log.debug("validate");
    	new OrderFormValidator().validate(orderForm, result);
        if (result.hasErrors()) {
        	log.debug("has errors");
        	return "form";
        }
        
        if (orderForm.getId()!=null && orderForm.getId()>0 ) {
        	orderId=orderForm.getId();
        	order = orderhDao.findById(orderForm.getId());
        	
        	order.setId(orderForm.getId());
        	order.setWalletId(orderForm.getWalletId());
        	order.setFundingsourceId(orderForm.getFundingsourceId());
        	order.setType(orderType);
        	order.setAmount(orderForm.getAmount());
        	
        	msg = orderService.updateOrder(order);
        } 
        else{
    		msg = orderService.placeOrder(userId, orderForm.getWalletId(), orderForm.getFundingsourceId(), orderType, orderForm.getAmount());
    		order = (Order) msg.getExtra();
    		orderId=order.getId();
    	}
        
		log.debug("msg:"+msg);
		
		if (msg != null && msg.getStatus().equals(Config.MSG_ERROR)) {
        	result.reject(msg.getMsg(), msg.getMsg());
        	orderForm.setMode("form");
        	return "form";
        }
		
		model.addAttribute("order",  calcFees(order));
		orderForm.setMode("process");
		orderForm.setId(orderId);
		
		return "confirm";
    }
    
    @ModelAttribute("orderTypesHash")
    public Map<String, String> getOrderTypesHash() {
    	Map<String, String> m = new HashMap<String, String>();
    	if (Config.SelectOptionDefaultEntry) m.put(Config.SelectOptionDefaultValue, Config.SelectOptionDefaultLabel);

    	m.put("buy", "Buy");
    	m.put("sell", "Sell");
    	return m;
    }
    

    
    @ModelAttribute("fundingSourcesHash")
    public List<LongSelOption> getFundingSourcesHash( ) {
    	Long userId = authenticationFacade.getUserId();
    	List<Fundingsource> items = fundingsourceDao.findByActiveUserId(userId);
    	return Util.getOptionsList ( fundingsourceDao.convertToMap(items) );
    }
    
   
    @ModelAttribute("walletsHash")
    public List<LongSelOption> getWalletsHash1( ) {
    	Long userId = authenticationFacade.getUserId();
    	List<Wallet> items = walletDao.findByActiveUserId(userId);    	
    	return Util.getOptionsList ( walletDao.convertToMap(items) );
    }
    
    @ModelAttribute("transactionBaseFee")
    public Float getTransactionBaseFee() {
    	return propertiesService.getTtransactionBaseFee();
    }
    
    @ModelAttribute("transactionPctFee")
    public Float getTransactionPctFee() {
    	return propertiesService.getTransactionPctFee();
    }
    
    
    @ModelAttribute("fxSell")
    public Float getFxSell() {
    	return 600f;
    }
    
    @ModelAttribute("fxBuy")
    public Float getFxBuy() {
    	return 550f;
    }
    
}
