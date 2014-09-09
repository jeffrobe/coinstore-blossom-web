package com.coin.components;


import info.magnolia.module.blossom.annotation.Template;
import info.magnolia.module.blossom.annotation.TemplateDescription;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.ValueFormatException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.coin.areas.Main;
import com.coin.web.common.Config;
import com.coin.dao.OrderDao;
import com.coin.model.Message;
import com.coin.web.service.IAuthenticationFacade;
import com.coin.web.service.OrderService;
 
@Controller
@Template(title = "order Action", id = "coinstore-blossom:components/orderAction")
@TemplateDescription("A order action ...")
@Main
public class OrderActionComponent {
	private static final Logger log = Logger.getLogger(OrderActionComponent.class);
	@Autowired
	private OrderDao orderhDao;
 
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	OrderService orderService;
	

	@Autowired
	private IAuthenticationFacade authenticationFacade;
	 
	@RequestMapping(value = "/orderAction", method = RequestMethod.GET)
    public String viewForm( ModelMap model, Node content,
    		@RequestParam (value = "id", required = false) Long orderId, 
    		@RequestParam (value = "action", required = false) String action, 
    		HttpServletRequest request ) throws ValueFormatException, PathNotFoundException, RepositoryException {
		
		boolean hasError=false;
		List<String> errors = new ArrayList<String> ();

		Long userId = authenticationFacade.getUserId();
    	 
    	if (orderId == null) {
    		log.error("order id is null");
    		return "components/error.ftl";    		
    	}
    	

    	if (action != null && action.equals("cancel")) {
    		log.debug("resend deposits");
    		Message msg = orderService.cancelOrder(userId, orderId);
    		log.debug(msg);
    		if (!msg.getMsg().equals(Config.MSG_SUCCESS)) {
				errors.add(messageSource.getMessage(msg.getMsg(), null, msg.getMsg(), LocaleContextHolder.getLocale()));
				hasError=true;
    		}
    		 
    		else return Config.memberRedirect;
    	}
    	
        return Config.memberRedirect;
    }
 

}
