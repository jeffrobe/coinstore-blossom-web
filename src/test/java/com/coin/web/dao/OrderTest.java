package com.coin.web.dao;
 
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.coin.common.BaseConfig;
import com.coin.dao.OrderDao;
import com.coin.model.Order;
import com.coin.model.Message;
import com.coin.web.service.OrderService;

import org.junit.Ignore;
import org.junit.Test;

 
public class OrderTest extends AbstractDaoTest {

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderDao orderhDao;


	private static Long userId=1l; 
	private static Long fundingsourceId=1l; 
	private static Long walletId=9l; 
	 
	
	@Test
	 
	public void placeOrderdao() throws Exception {
		log.info("placeOrderdao");
		Order orderh = new Order(userId, walletId, fundingsourceId, "buy", 10.0f);
		orderh.setStatus(BaseConfig.statusOrderDraft);
		orderh.setEntered(new Date());
		
		Order o=orderhDao.saveFlush(orderh);
		
		log.info("id: "+o.getId());
	}
	
	@Test
 
	public void placeOrder() throws Exception {
		log.info("placeOrder");
		Float amount = new Float(12.0);
		String type=BaseConfig.ORDER_SELL;
		 
		Message msg = orderService.placeOrder(userId, walletId, fundingsourceId, type, amount);
		
		log.info("msg: "+msg);
	}
	
	@Test
	 

	public void cancelOrder() throws Exception {
		log.info("cancelOrder");
		  
		Long orderId = 1l;
		Message msg = orderService.cancelOrder(userId, orderId);
		
		log.info("msg: "+msg);
	}
	  
	
	@Test
	@Ignore

	public void confirmOrder() throws Exception {
		log.info("confirm Order");
		  
		Long orderId = 1l;
		Message msg = orderService.confirmOrder(userId, orderId);
		
		log.info("msg: "+msg);
	}
	  
	
	@Test
	@Ignore

	public void getUserOrders() throws Exception {
		log.info("getUserOrders "+userId);

		List<Order> lst = orderhDao.findByUserId(userId);
		
		log.info("lst: "+lst);
	}
	
}