package com.coin.components;


import info.magnolia.module.blossom.annotation.TabFactory;
import info.magnolia.module.blossom.annotation.Template;
import info.magnolia.module.blossom.annotation.TemplateDescription;
import info.magnolia.ui.form.config.TabBuilder;
import info.magnolia.ui.framework.config.UiConfig;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.coin.areas.Main;
import com.coin.web.common.Config;
import com.coin.web.common.Util;
import com.coin.dao.UserDao;
import com.coin.web.forms.TokenFormValidator;
import com.coin.web.forms.TokenForm;
import com.coin.model.Message;
import com.coin.model.User;
import com.coin.web.service.RegistrationService;
 
@Controller
@Template(title = "token Form", id = "coinstore-blossom:components/tokenForm")
@TemplateDescription("A token form ...")
@Main
public class TokenFormComponent {
	private static final Logger log = Logger.getLogger(TokenFormComponent.class);
	@Autowired
	private UserDao userDao;
	
	@Autowired
	RegistrationService registrationService;

	@RequestMapping(value = "/tokenForm", method = RequestMethod.GET)
    public String viewForm( ModelMap model, 
    		HttpServletRequest request, @ModelAttribute TokenForm tokenForm) {
		
		Long userId = Util.getLoggedUserId();
    	
    	log.info("user id: "+userId);
    	model.addAttribute("userId", userId);
        
       	tokenForm.setUserId(userId);
         
        return "components/tokenForm.ftl";
      
    }

	@RequestMapping(value = "/tokenForm", method = RequestMethod.POST)
    public String handleSubmit(@ModelAttribute TokenForm tokenForm, BindingResult result, Node content,  HttpServletRequest request
    		) throws RepositoryException {

		Long userId = Util.getLoggedUserId();
    	log.info("user id: "+userId);
        if (userId == null) return "components/logout.ftl";
        
		new TokenFormValidator().validate(tokenForm, result);
        if (result.hasErrors()) return "components/tokenForm.ftl";
 
        
        Message msg =  registrationService.validateToken(tokenForm.getToken());
        if (msg.getStatus().equals("error")) {
   			result.reject("data error", msg.getMsg());
        	return "components/tokenForm.ftl";
        }    

       	return "website:" + Config.memberHome;
    }

    
}
