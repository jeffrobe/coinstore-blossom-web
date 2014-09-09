package com.coin.components;


import info.magnolia.module.blossom.annotation.TabFactory;
import info.magnolia.module.blossom.annotation.Template;
import info.magnolia.module.blossom.annotation.TemplateDescription;
import info.magnolia.ui.form.config.TabBuilder;
import info.magnolia.ui.framework.config.UiConfig;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.coin.areas.Main;
import com.coin.dao.FundingsourceDao;
import com.coin.web.common.Config;
import com.coin.web.common.Util;
import com.coin.web.forms.FundingsourceForm;
import com.coin.web.forms.FundingsourceFormValidator;
import com.coin.model.Fundingsource;
import com.coin.model.Message;
import com.coin.web.service.FundingsourceService;
import com.coin.web.service.IAuthenticationFacade;
 
@Controller
@Template(title = "Fundingsource Form", id = "coinstore-blossom:components/fundingsourceForm")
@TemplateDescription("A fundingsource form ...")
@Main
public class FundingsourceFormComponent {
	private static final Logger log = Logger.getLogger(FundingsourceFormComponent.class);
	private static final String formPage="components/fundingsourceForm.ftl";

	@Autowired
	private IAuthenticationFacade authenticationFacade;
	
	@Autowired
	private FundingsourceDao fundingsourceDao;
	
	@Autowired
	FundingsourceService fundingsourceService;
	 
	@RequestMapping(value = "/fundingsource", method = RequestMethod.GET)
    public String viewForm( ModelMap model, 
    		@RequestParam (value = "id", required = false) Long id, 
    		HttpServletRequest request, @ModelAttribute FundingsourceForm fundingsourceForm) {
		 
 
    	
    	if (id != null) {
    		Fundingsource fundingsource = fundingsourceDao.findById(id);
    		fundingsourceForm.setId(id);
 
    		fundingsourceForm.setType(fundingsource.getType());
    		fundingsourceForm.setAccount(fundingsource.getAccount());
    		fundingsourceForm.setRouting(fundingsource.getRouting());
    		fundingsourceForm.setName(fundingsource.getName());
    		fundingsourceForm.setInstitution(fundingsource.getInstitution());
    		fundingsourceForm.setCity(fundingsource.getCity());
    		fundingsourceForm.setState(fundingsource.getState());
    		fundingsourceForm.setPostalcode(fundingsource.getPostalcode());
    	}
    	
        return formPage;
      
    }

	@RequestMapping(value = "/fundingsource", method = RequestMethod.POST)
    public String handleSubmit(ModelMap model, @ModelAttribute FundingsourceForm fundingsourceForm,
    		BindingResult result, Node content,  HttpServletRequest request
    		) throws RepositoryException {
  
        
		new FundingsourceFormValidator().validate(fundingsourceForm, result);
	 
        if (result.hasErrors()) return formPage;
        
    	Long userId = authenticationFacade.getUserId(); 

		Fundingsource fundingsource = new Fundingsource();
		fundingsource.setType(fundingsourceForm.getType());
		fundingsource.setAccount(fundingsourceForm.getAccount());
		fundingsource.setRouting(fundingsourceForm.getRouting());
		fundingsource.setName(fundingsourceForm.getName());
		
		log.debug("fundingsourceForm id: "+ fundingsourceForm.getId());
		
		Message msg=null;
		if (fundingsourceForm.getId() != null && fundingsourceForm.getId()>0) {
			fundingsource.setId(fundingsourceForm.getId());
			fundingsource.setUserId(userId);
			msg = fundingsourceService.updateSource(fundingsource);
    	}
		else {
			fundingsource.setUserId(userId);
			msg = fundingsourceService.newSource(fundingsource);
		}

		log.debug(msg);
        if (msg != null && msg.getStatus().equals(Config.MSG_SUCCESS))
           	return Config.memberRedirect;

        else {
        	if (msg.getMsg().equals("bad_routing")) result.rejectValue("routing", "bank.routing.invalid", "bank.routing.invalid");
        	if (msg.getMsg().equals("name_exist")) result.rejectValue("name", "bank.name.inuse", "bank.name.inuse");
        	result.reject(msg.getMsg(),msg.getMsg());
        	 
        	return formPage;
        }
        	
    }
    
    @ModelAttribute("fundingsourceTypesHash")
    public Map<String, String> getOrderTypesHash() {
    	Map<String, String> m = new HashMap<String, String>();
    	if (Config.SelectOptionDefaultEntry) m.put(Config.SelectOptionDefaultValue, Config.SelectOptionDefaultLabel);

    	m.put("checking", "Checking");
    	m.put("savings", "Savings");
    	return m;
    }
    
}
