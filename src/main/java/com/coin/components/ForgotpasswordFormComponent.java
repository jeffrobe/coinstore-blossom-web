package com.coin.components;


import info.magnolia.module.blossom.annotation.Template;
import info.magnolia.module.blossom.annotation.TemplateDescription;

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
import com.coin.common.BaseConfig;
import com.coin.dao.UserDao;
import com.coin.web.forms.ProfileForm;
import com.coin.web.forms.ForgotpasswordFormValidator;
import com.coin.model.Message;
import com.coin.web.service.EmailService;
import com.coin.web.service.RegistrationService;
 
 
@Controller
@Template(title = "Forgot Password Form", id = "coinstore-blossom:components/forgotpasswordForm")
@TemplateDescription("A forgotpassword form ...")
@Main
public class ForgotpasswordFormComponent  extends BaseComponent {
	private static final String formPage = "components/forgotpasswordForm.ftl";

	@Autowired
	private UserDao userDao;
	
	@Autowired
	RegistrationService registrationService;

	@Autowired
	EmailService emailService;

    @RequestMapping(value = "/forgotpassword", method = RequestMethod.GET)
    public String viewForm(ModelMap model, @ModelAttribute ProfileForm profileForm) {
    	return formPage;   
    }

    @RequestMapping(value = "/forgotpassword", method = RequestMethod.POST)
   	public String handleSubmit(ModelMap model, @ModelAttribute ProfileForm profileForm,
        		BindingResult result, Node content,  HttpServletRequest request
        		) throws RepositoryException  {

        new ForgotpasswordFormValidator().validate(profileForm, result);
    	if ( getrequireToken() && ! getseperateTokenAuth() ){
    		if (profileForm.getToken()==null || profileForm.getToken().isEmpty()) result.rejectValue("token", "token.required","token.required");
    		else {
    			Message msg = null; //registrationService.validateToken(profileForm.getToken());
    			if (msg.getStatus().equals(BaseConfig.MSG_ERROR)) result.reject(msg.getMsg(), msg.getMsg());
    		}
    	}

    	if (result.hasErrors()) return formPage;   

        String email = profileForm.getEmail();
        Message msg = null; //registrationService.forgotPassword(email);
        if (msg.getStatus().equals(BaseConfig.MSG_ERROR)) {
        	log.info( msg.getMsg());
    		result.reject( msg.getMsg(),  msg.getMsg());
    		return formPage;        	
        } 
        else {
        	model.put("email", email);
        	return "components/forgotpasswordSuccess.ftl";       
        }
      
    }


    @ModelAttribute("seperateTokenAuth")
    public Boolean getseperateTokenAuth() {
    	return propertiesService.getSeperateTokenAuth();
    }

    @ModelAttribute("requireToken")
    public Boolean getrequireToken() {
    	return propertiesService.getRequireTokenAuth();
    }
    

}
