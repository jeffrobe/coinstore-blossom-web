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
import com.coin.web.common.Config;
import com.coin.web.forms.OrderForm;
 
@Controller
@Template(title = "Buy Coin", id = "coinstore-blossom:components/buycoin")
@TemplateDescription("A buy coin ...")
@Main

public class BuycoinComponent extends OrderBaseComponent {
	private static final Logger log = Logger.getLogger(OrderBaseComponent.class);
	private String formPage = "components/buycoinForm.ftl";
	private String confirmationPage = "components/buycoinConfirmation.ftl";
	private String formRedirect = "redirect:buycoin.html";
	
	@RequestMapping(value = "/buycoin", method = RequestMethod.GET)
    public String viewForm( ModelMap model, 
    		@RequestParam (value = "id", required = false) Long id, 
    		HttpServletRequest request, @ModelAttribute OrderForm orderForm) {

		String retStr = super.viewForm(model, id, request, orderForm); 
		return formPage;
    }

	@RequestMapping(value = "/buycoin", method = RequestMethod.POST)
    public String handleSubmit(ModelMap model, @ModelAttribute OrderForm orderForm,
    		BindingResult result, Node content,  HttpServletRequest request
    		) throws RepositoryException {
 
		String retStr = super.handleSubmit("buy", model, orderForm, result, content, request);
		if (retStr.equals("form")) return formPage;
		else if (retStr.equals("confirm")) return confirmationPage;
		else if (retStr.equals("formredir")) return formRedirect+"&?id="+orderForm.getId();
		else return Config.memberRedirect;
		
    }
  
	
}
