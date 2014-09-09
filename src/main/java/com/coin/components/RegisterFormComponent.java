package com.coin.components;


import info.magnolia.module.blossom.annotation.Template;
import info.magnolia.module.blossom.annotation.TemplateDescription;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.servlet.http.HttpServletRequest;

import net.tanesha.recaptcha.ReCaptchaImpl;

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
import com.coin.common.BaseConfig;
import com.coin.web.common.Util;
import com.coin.dao.UserDao;
import com.coin.web.forms.ProfileForm;
import com.coin.web.forms.RegisterFormValidator;
import com.coin.model.Message;
import com.coin.model.User;
import com.coin.web.service.RegistrationService;
 
@Controller
@Template(title = "Register Form", id = "coinstore-blossom:components/registerForm")
@TemplateDescription("A register form ...")
@Main
public class RegisterFormComponent  extends BaseComponent {
	private static final String formPage =  "components/registerForm.ftl";
	private static final String succcessPage =  "components/registerSuccess.ftl";

	@Autowired
	private UserDao userDao;
	
	@Autowired
	RegistrationService registrationService;


	@Autowired
	ReCaptchaImpl reCaptcha;
	

	@RequestMapping(value = "/register", method = RequestMethod.GET)
    public String viewForm(ModelMap model, @ModelAttribute ProfileForm profileForm) {
		model.put("requireImageAuth", propertiesService.getRequireImageAuth());
        return formPage;
    }

	@RequestMapping(value = "/register", method = RequestMethod.POST)
    public String handleSubmit(ModelMap model, @ModelAttribute ProfileForm profileForm, BindingResult result, Node content,  HttpServletRequest request,
            @RequestParam (value = "recaptcha_challenge_field", required = false)  String challangeField,
            @RequestParam (value = "recaptcha_response_field", required = false)  String responseField
    		) throws RepositoryException {

		new RegisterFormValidator().validate(profileForm, result);

        if (result.hasErrors()) return formPage;
 
        if (getrequireImageAuth()) {
        	if (propertiesService.getOverrideImageAuth() && responseField.equals(propertiesService.getOverrideImageAuthValue())) {}
        	else {	
	        	boolean captcha = Util.isValidRecaptcha(reCaptcha, request.getRemoteAddr(), challangeField, responseField);
	        	
				if(!captcha) {
					result.reject("invalid.captcha", "invalid.captcha");
		    		return formPage;
				}
        	}
        }
        
    	Message msg = registrationService.newRegistration (profileForm.getEmail(), profileForm.getPassword() );

    	if ( msg.getStatus().equals(BaseConfig.MSG_ERROR)  ) {
			result.reject( msg.getMsg(), msg.getMsg());
    		result.rejectValue("email", msg.getMsg(), msg.getMsg());
    		
    		return formPage;
        }
 
    	else {
			log.info("registration succeeded: "+msg.getExtra());
    		Object extra = null;
			extra = msg.getExtra();
			User user = null;
			if(extra!=null) user = (User) extra;
			log.info("email: "+user.getEmail());

		    model.addAttribute("email", user.getEmail());
		    model.addAttribute("user", user);
    		return succcessPage;
    		
    	}

    }
	
 
    @ModelAttribute("requireImageAuth")
    public Boolean getrequireImageAuth() {
    	return propertiesService.getRequireImageAuth();
    }

 
   
}
