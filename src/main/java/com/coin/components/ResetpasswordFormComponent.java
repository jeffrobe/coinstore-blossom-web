package com.coin.components;


import info.magnolia.cms.core.Content;
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
import com.coin.web.forms.ResetpasswordFormValidator;
import com.coin.model.Message;
import com.coin.model.User;
import com.coin.web.service.RegistrationService;
 
@Controller
@Template(title = "Reset Password Form", id = "coinstore-blossom:components/resetPasswordForm")
@TemplateDescription("A reset password form ...")
@Main
public class ResetpasswordFormComponent {
	private static final Logger log = Logger.getLogger(ResetpasswordFormComponent.class);
	@Autowired
	private UserDao userDao;
	
	@Autowired
	RegistrationService registrationService;

	private String formPage = "components/resetPasswordForm.ftl";
 
    @RequestMapping(value = "/resetPasswordForm", method = RequestMethod.GET)
    public String viewForm( ModelMap model, HttpServletRequest request,
    		@ModelAttribute ProfileForm profileForm, BindingResult result, Node content,
    		@RequestParam (value = "hash", required = false) String hash,
    		@RequestParam (value = "email", required = false) String email) 
    		throws RepositoryException {

		
    	log.debug("email: "+email+ " hash: "+hash);
    	
    	Message msg = registrationService.getUserFromHash(email, hash );
    	if (msg.getStatus().equals(BaseConfig.MSG_ERROR)) {
        	log.info(msg);
    		result.reject( msg.getMsg(),  msg.getMsg());
    	}

    	else {
    		User user = (User) msg.getExtra();
     		request.getSession().setAttribute("userId",user.getId());
    	}
        return formPage;
    }
    


		
    @RequestMapping(value = "/resetPasswordForm", method = RequestMethod.POST)
    public String handleSubmit(ModelMap model,
    		@ModelAttribute ProfileForm profileForm, 
    		BindingResult result, Node content,  HttpServletRequest request
  		) throws RepositoryException {
		

    	Long userId = Util.getSessionUserId(request);

        new ResetpasswordFormValidator().validate(profileForm, result);    
        if (result.hasErrors()) return formPage;

		Message msg = registrationService.resetPassword (userId, profileForm.getPassword() );
		log.debug(msg);
		
		if (msg.getStatus().equals(BaseConfig.MSG_SUCCESS)) return "website:" + content.getProperty("successPage").getString();
		else {
			result.rejectValue("email", msg.getMsg(), msg.getMsg());
			return formPage;
	    }
    }

    @TabFactory("Content")
    public void contentTab(UiConfig cfg, TabBuilder tab) {
        tab.fields(
        	cfg.fields.pageLink("successPage").label("Success page")
        );
    }
}
