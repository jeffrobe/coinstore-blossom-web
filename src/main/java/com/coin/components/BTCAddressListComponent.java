package com.coin.components;


import info.magnolia.module.blossom.annotation.Template;
import info.magnolia.module.blossom.annotation.TemplateDescription;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.coin.areas.Main;
import com.coin.model.Btcaddress;
import com.coin.web.service.IAuthenticationFacade;
import com.coin.web.service.impl.BTCUserServiceImpl;
 
@Controller
@Template(title = "BTC Address List Component", id = "coinstore-blossom:components/btcaddressList")
@TemplateDescription("BTC Address List Component ...")
@Main

@SessionAttributes("btcaddresses")

public class BTCAddressListComponent extends BaseComponent {
	private String compPage = "components/btcAddressList.ftl";

	@Autowired
	private IAuthenticationFacade authenticationFacade;
	
	@Autowired
	private BTCUserServiceImpl btcUserService;

 
	BTCAddressListComponent() {
	}
	
	@RequestMapping("/btcaddressList")
		public String render() {
		return compPage;
	}


	@ModelAttribute("btcaddresses")
	public List<Btcaddress> getBtcaddresses () {
		log.info("getBtcaddresses from comp");
	    Long userId = authenticationFacade.getUserId();
	    List<Btcaddress> lst = null; //btcUserService.getEnhancedBtcaddresses(userId);
	    	
	    return lst;
	}

    
}
