package com.coin.components;


import java.util.List;
import java.util.Map;

import info.magnolia.module.blossom.annotation.TabFactory;
import info.magnolia.module.blossom.annotation.Template;
import info.magnolia.module.blossom.annotation.TemplateDescription;
import info.magnolia.ui.form.config.TabBuilder;
import info.magnolia.ui.framework.config.UiConfig;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.coin.areas.Main;
import com.coin.spring.security.CustomAuthenticationProvider;
import com.coin.spring.security.CustomWebAuthenticationDetails;
import com.coin.common.BaseConfig;
import com.coin.web.common.Util;
import com.coin.dao.UserDao;
import com.coin.web.forms.FundingsourceForm;
import com.coin.web.forms.FundingsourceFormValidator;
import com.coin.web.forms.LoginFormValidator;
import com.coin.web.forms.ProfileForm;
import com.coin.model.Message;
import com.coin.model.Notificationtype;
import com.coin.model.User;
import com.coin.web.service.RegistrationService;
import com.coin.web.service.impl.BTCTickerServiceImpl;
import com.vaadin.server.ErrorMessage;
 
@Controller
@Template(title = "Ticker Page", id = "coinstore-blossom:components/tickerPage")
@TemplateDescription("A ticker page ...")
@Main
public class BTCTickerComponent extends BaseComponent {
	private String compPage = "components/btcTicker.ftl";
	
	@Autowired
	private BTCTickerServiceImpl btcTickerService;

	 
	BTCTickerComponent() {
	}
	

	@RequestMapping("/tickerPage")
	public String render() {
	return compPage;
	}

	/*
    @ModelAttribute("btcTickers")
    public List<BTCTicker> getBTCTicker( ) {
    	List<BTCTicker> mp = btcTickerService.getTickers();
    	log.debug("getBTCTicker:" +mp );
    	return mp;	
    }
  
    */
}
