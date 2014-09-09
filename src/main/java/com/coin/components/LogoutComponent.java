package com.coin.components;


import info.magnolia.module.blossom.annotation.Template;
import info.magnolia.module.blossom.annotation.TemplateDescription;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.coin.areas.Main;
import com.coin.web.common.Util;

@Controller
@Template(title = "Log out", id = "coinstore-blossom:components/logout")
@TemplateDescription("A log out ...")
@Main
public class LogoutComponent {
	private static final Logger log = Logger.getLogger(LogoutComponent.class);
	private  final String formPage = "components/logout.ftl";
	
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String view( ModelMap model, HttpServletRequest request ) {

    	 HttpSession session = request.getSession();
    	 session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, null);
    	
    	return formPage;
    }
  
}
