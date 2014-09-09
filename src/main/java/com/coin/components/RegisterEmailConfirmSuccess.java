package com.coin.components;


import info.magnolia.module.blossom.annotation.Template;
import info.magnolia.module.blossom.annotation.TemplateDescription;

import javax.jcr.RepositoryException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.coin.areas.Main;

@Template(title = "Register Email Confirm Success", id = "coinstore-blossom:components/registerEmailConfirmSuccess")
@TemplateDescription("Register Email Confirm Success")
@Controller
@Main
public class RegisterEmailConfirmSuccess {
 
	@RequestMapping(value = "/registerEmailConfirmSuccess", method = RequestMethod.GET)
    public String viewForm( ModelMap model) 
    		throws RepositoryException {
	
		return "components/registerEmailConfirmSuccess.ftl";
		
    }
 
   
}


