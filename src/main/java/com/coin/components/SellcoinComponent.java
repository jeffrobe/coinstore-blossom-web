package com.coin.components;


import info.magnolia.module.blossom.annotation.Template;
import info.magnolia.module.blossom.annotation.TemplateDescription;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.coin.areas.Main;
import com.coin.web.common.Config;
import com.coin.web.common.Util;
import com.coin.web.forms.OrderForm;
 
@Controller
@Template(title = "Sell Coin", id = "coinstore-blossom:components/sellcoin")
@TemplateDescription("A sell coin ...")
@Main
 

public class SellcoinComponent extends OrderBaseComponent {
	private String formPage = "components/sellcoinForm.ftl";
	private String confirmationPage = "components/sellcoinConfirmation.ftl";
	private String formRedirect = "redirect:sellcoin.html";
	
	@RequestMapping(value = "/sellcoin", method = RequestMethod.GET)
    public String viewForm( ModelMap model, 
    		@RequestParam (value = "id", required = false) Long id, 
    		HttpServletRequest request, @ModelAttribute OrderForm orderForm)
	{
		 
		String retStr = super.viewForm(model, id, request, orderForm);  
		return formPage;
    }

	@RequestMapping(value = "/sellcoin", method = RequestMethod.POST)
    public String handleSubmit(ModelMap model, @ModelAttribute OrderForm orderForm,
    		BindingResult result, Node content,  HttpServletRequest request
    		) throws RepositoryException {
		 
		String retStr = super.handleSubmit("sell", model, orderForm, result, content, request);
		//return Config.memberRedirect;
		 
		if (retStr.equals("form")) return formPage;
		else if (retStr.equals("confirm")) return confirmationPage;
		else if (retStr.equals("formredir")) return formRedirect+"&?id="+orderForm.getId();
		else return Config.memberRedirect;
    }
  
}
