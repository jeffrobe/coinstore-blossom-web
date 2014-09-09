package com.coin.components;


import info.magnolia.module.blossom.annotation.Template;
import info.magnolia.module.blossom.annotation.TemplateDescription;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.coin.areas.Main;

import com.coin.web.service.IAuthenticationFacade;
import com.coin.web.service.impl.BTCUserServiceImpl;
 
@Controller
@Template(title = "BTC Info Component", id = "coinstore-blossom:components/btcinfo")
@TemplateDescription("BTC Info Component ...")
@Main

@SessionAttributes("btcaddresses")

public class BTCInfoComponent extends BaseComponent {
	private final String compPage = "components/btcinfo.ftl";
		
	@Autowired
	private BTCUserServiceImpl btcUserService;

	BTCInfoComponent() { }
	
	@RequestMapping("/btcinfo")
		public String render(ModelMap model,@RequestParam (value = "id", required = true) String id ) {

		//BtcaddressEnhanced btc = btcUserService.getEnhancedBtcaddress(id);
		//model.addAttribute("btc", btc);
		return compPage;
	}

	 
    
}
