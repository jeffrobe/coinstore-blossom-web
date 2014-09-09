package com.coin.components;


import info.magnolia.module.blossom.annotation.Template;
import info.magnolia.module.blossom.annotation.TemplateDescription;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.coin.areas.Main;
import com.coin.dao.FundingsourceDao;
import com.coin.dao.OrderDao;
import com.coin.dao.UserDao;
import com.coin.dao.WalletDao;
import com.coin.model.Fundingsource;
import com.coin.model.Order;
import com.coin.model.Wallet;
import com.coin.web.service.IAuthenticationFacade;
import com.coin.web.service.RegistrationService;
import com.coin.web.service.impl.BTCTickerServiceImpl;
 

@Controller
@Template(title = "Login Home", id = "coinstore-blossom:components/loginHome")
@TemplateDescription("A login home ...")
@Main
public class LoginHomeComponent extends BaseComponent {
 	@Autowired
	private RegistrationService registrationService;

	@Autowired
	private FundingsourceDao fundingsourceDao;

	@Autowired
	private WalletDao walletDao;

	@Autowired
	private OrderDao orderhDao;
	
	@Autowired
	private UserDao userDao;
	
 
	
	@Autowired
	private BTCTickerServiceImpl btcTickerService;

	@Autowired
	private IAuthenticationFacade authenticationFacade;
	
    @RequestMapping(value = "/loginHome", method = RequestMethod.GET)
    public String view( ModelMap model,  
    		@RequestParam (value = "user_id", required = false) Long userId 
    	) {
    	
    	userId = authenticationFacade.getUserId();
    	model.addAttribute("userId", userId);
    	
      	return "components/loginHome.ftl";
        
    }
 
    /*
    @ModelAttribute("fundingsources")
    public List<Fundingsource> getFundingsources () {
    	Long userId = authenticationFacade.getUserId();
    	return fundingsourceDao.findByUserId(userId);
    }

    @ModelAttribute("wallets")
    public List<Wallet> getWallets () {
    	Long userId = authenticationFacade.getUserId();
    	return walletDao.listObjByUserId(userId);
    }
  
    @ModelAttribute("orders")
    public List<Order> getOrders () {
    	Long userId = authenticationFacade.getUserId();
    	return orderhDao.findByUserId(userId);
    }
    */
}
