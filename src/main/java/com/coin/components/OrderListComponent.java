package com.coin.components;


import info.magnolia.cms.core.Content;
import info.magnolia.module.blossom.annotation.Template;
import info.magnolia.module.blossom.annotation.TemplateDescription;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.coin.areas.Main;
import com.coin.dao.OrderDao;
import com.coin.web.common.Util;
import com.coin.model.Order;
 
@Template(title = "Order List", id = "coinstore-blossom:components/orderList")
@TemplateDescription("List of Orders")
@Controller
@Main

@SessionAttributes("orders")
public class OrderListComponent {
  
	@Autowired
	private OrderDao orderDao;

    @RequestMapping("/orderList")
    public String render(ModelMap model, HttpSession session, 
    		HttpServletRequest request, Content content) {

    	Long userId = Util.getLoggedUserId();
    	
        return "components/orderList.ftl";
    }

	@ModelAttribute("orders")
	public List<Order> getOrders () {
		Long userId = Util.getLoggedUserId();
		List<Order> lst =  orderDao.findByUserId(userId);
    		
	    return lst;
	}
   
}


