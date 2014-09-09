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

import com.coin.areas.Main;
import com.coin.web.forms.OrderForm;
 
@Controller
@Template(title = "Transfer Coin Form", id = "coinstore-blossom:components/transfercoinForm")
@TemplateDescription("A transfer coin order form ...")
@Main

public class TransfercoinComponent extends OrderBaseComponent {
	private String formPage = "components/transfercoinForm.ftl";
	private String confirmationPage = "components/transfercoinConfirmation.ftl";

	@RequestMapping(value = "/transfercoinForm", method = RequestMethod.GET)
    public String viewForm( ModelMap model, 
    		@RequestParam (value = "id", required = false) Long id, 
    		HttpServletRequest request, @ModelAttribute OrderForm orderForm) {

		super.viewForm(model, id, request, orderForm); 
		return formPage;
    }

	@RequestMapping(value = "/transfercoinForm", method = RequestMethod.POST)
    public String handleSubmit(ModelMap model, @ModelAttribute OrderForm orderForm,
    		BindingResult result, Node content,  HttpServletRequest request
    		) throws RepositoryException {
 
		super.handleSubmit("transfer", model,  orderForm, result, content, request);
		return confirmationPage;
    }
  
	
}
