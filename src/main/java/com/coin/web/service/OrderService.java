package com.coin.web.service;
 
import java.math.BigDecimal;

import com.coin.model.Message;
import com.coin.model.Order;
 
public interface OrderService {

	Message placeOrder(Long userId, Long walletId, Long fundingsourceId,String type, Float amount);

	Message cancelOrder(Long userId, Long orderId);

	Message updateOrder(Order order);

	Message confirmOrder(Long userId, Long orderId);
 
}
