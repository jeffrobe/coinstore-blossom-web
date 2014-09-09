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
import org.springframework.web.bind.annotation.RequestParam;

import com.coin.areas.Main;
import com.coin.dao.UserDao;
import com.coin.common.BaseConfig;
import com.coin.web.common.Util;
import com.coin.web.forms.ProfileForm;
import com.coin.web.forms.RegisterEmailConfirmFormValidator;
import com.coin.model.Message;
import com.coin.model.User;
import com.coin.web.service.RegistrationService;

@Template(title = "Register Email Confirm", id = "coinstore-blossom:components/registerEmailConfirm")
@TemplateDescription("Register Email Confirm")
@Controller
@Main
public class RegisterEmailConfirm {
	private static final Logger log = Logger.getLogger(RegisterEmailConfirm.class);

	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	RegistrationService registrationService;
	private String formPage="components/registerEmailConfirm.ftl";

	@RequestMapping(value = "/registerEmailConfirm", method = RequestMethod.GET)
    public String viewForm( ModelMap model, HttpServletRequest request,
    		@ModelAttribute ProfileForm profileForm, BindingResult result, Node content,
    		@RequestParam (value = "hash", required = false) String hash, @RequestParam (value = "email", required = false) String email) 
    		throws RepositoryException {
		boolean showTerms=true;

		Message msg = registrationService.getUserFromHash(email, hash);
		if (msg.getStatus().equals(BaseConfig.MSG_ERROR)) {
			result.reject(msg.getMsg(), msg.getMsg());
			showTerms=false;
			return formPage;	
		}
		User user = (User)msg.getExtra();
		if (user== null){
			result.reject("bad yuser", "bad user");
			showTerms=false;
			return formPage;	
			
		}
 

		if (user.getStatus() != null && user.getStatus().equals(BaseConfig.statusActive)) {
			result.reject("registration.confirm.alreadyactive", "registration.confirm.alreadyactive");
			showTerms=false;			
		}

 		log.error("user.getId(): "+user.getId());
 		request.getSession().setAttribute("userId",user.getId());

		model.addAttribute("showTerms", showTerms);
		return formPage;	
    }

	@RequestMapping(value = "/registerEmailConfirm", method = RequestMethod.POST)
    public String handleSubmit(ModelMap model,
    		@ModelAttribute ProfileForm profileForm, 
    		BindingResult result, Node content,  HttpServletRequest request
  		) throws RepositoryException {
		
		new RegisterEmailConfirmFormValidator().validate(profileForm, result);


		if (result.hasErrors()) {
			model.addAttribute("showTerms", true);
			return formPage;
		}

		
		Long userId = Util.getSessionUserId(request);
		if (userId== null || userId<1) {
    		result.reject("no user id","no user id");
    		model.addAttribute("showTerms", false);
    		return formPage;
			
		}
		
    	Message msg = registrationService.confirmNewUserTos( userId );

    	if (msg.getStatus().equals("error")) {
    		result.reject(msg.getMsg(), msg.getMsg());
    		model.addAttribute("showTerms", true);
    		return formPage;
    	}
    	
    	return "website:" + content.getProperty("successPage").getString();
    }

    @TabFactory("Content")
    public void contentTab(UiConfig cfg, TabBuilder tab) {
    	tab.fields(
        	cfg.fields.pageLink("successPage").label("Success page")
        );
    }
    
}


