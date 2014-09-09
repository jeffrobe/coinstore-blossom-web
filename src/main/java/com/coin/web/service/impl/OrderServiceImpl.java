package com.coin.web.service.impl;


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coin.web.common.Config;
import com.coin.dao.OrderDao;
import com.coin.dao.UserDao;
import com.coin.model.Email;
import com.coin.model.Message;
import com.coin.model.Order;
import com.coin.model.User;
import com.coin.web.service.PropertiesService;
import com.coin.web.service.EmailService;
import com.coin.web.service.OrderService;
import com.coin.web.service.RegistrationService;
 
 
@Service ("orerService")
public class OrderServiceImpl implements OrderService {
	private static final Logger log = Logger.getLogger(OrderService.class);
 
	@Autowired
	private OrderDao orderhDao;

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RegistrationService registrationService;

	@Autowired
	private EmailService emailService;

	@Autowired
	protected PropertiesService propertiesService;
	
	@Override
	public Message placeOrder(Long userId, Long walletId, Long fundingsourceId, String type, Float amount) {
		Order order = new Order(userId, walletId, fundingsourceId, type, amount);
		order.setStatus(Config.statusOrderDraft);
		order.setEntered(new Date());
		
		Order orderhNew=orderhDao.saveFlush(order);
		if(orderhNew.getId()<0) return new Message(Config.MSG_ERROR,"order_error");
	 
		
		Message msg = new Message(Config.MSG_SUCCESS,"new order: "+orderhNew.getId());
		msg.setExtra(orderhNew);
		return msg;
	}
	
	@Override
	public Message confirmOrder(Long userId, Long orderId) {
		Order orderh=orderhDao.findById(orderId);
		if(orderh.getId()<0) return new Message(Config.MSG_ERROR,"failed to create order");
		
		orderh.setStatus(Config.statusPending);
		orderh.setUpdated(new Date());
		orderhDao.update(orderh);
		
		Map<String, Object> data = new HashMap<String, Object>();
		Email emailObj = emailService.getEmailFromTemplate(Config.emailOrderConfirmTemplate, data);
		log.debug("emailObj: "+emailObj.getBodyHtml());
		
		User user = userDao.findById(userId);
		emailService.send(emailObj, user.getEmail(), propertiesService.getSendHtml());
		
		return new Message(Config.MSG_SUCCESS,"confirmed order: "+orderId);
	}

	@Override
	public Message updateOrder(Order order) {
		order.setUpdated(new Date());
		order.setStatus(Config.statusPending);
		orderhDao.update(order);
		
		return new Message(Config.MSG_SUCCESS,"update order: ");
	}
	 
	@Override
	public Message cancelOrder(Long userId, Long orderId) {
		Order orderh = orderhDao.findById(orderId);
		orderh.setStatus(Config.statusCancel);
		orderh.setEntered(new Date());
		
		orderhDao.update(orderh);
		
		return new Message(Config.MSG_SUCCESS,"cancel order: ");
	}
}
