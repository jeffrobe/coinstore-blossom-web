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
import com.coin.common.BaseConfig;
import com.coin.web.common.Util;
import com.coin.dao.UserDao;
import com.coin.web.forms.PhoneFormValidator;
import com.coin.web.forms.ProfileForm;
import com.coin.model.Message;
import com.coin.model.User;
import com.coin.web.service.RegistrationService;
 
@Controller
@Template(title = "Register Phone Form", id = "coinstore-blossom:components/phoneForm")
@TemplateDescription("A phone form ...")
@Main
public class PhoneFormComponent {
	private static final Logger log = Logger.getLogger(PhoneFormComponent.class);
	@Autowired
	private UserDao userDao;
	
	@Autowired
	RegistrationService registrationService;

	private String formPage = "components/phoneForm.ftl";

	@RequestMapping(value = "/phoneForm", method = RequestMethod.GET)
    public String viewForm( ModelMap model, 
    		HttpServletRequest request, @ModelAttribute ProfileForm profileForm) {
		
		Long userId = Util.getSessionUserId(request);
    	
    	log.info("user id: "+userId);
    	if (userId== null || userId<1)  return "components/logout.ftl";

        
    	User user = userDao.findById(userId);
    	profileForm.setEmail(user.getEmail());
    	profileForm.setPhone(user.getPhone());
       	profileForm.setId(user.getId());
 
        return formPage;
      
    }

	@RequestMapping(value = "/phoneForm", method = RequestMethod.POST)
    public String handleSubmit(@ModelAttribute ProfileForm profileForm, BindingResult result, Node content,  HttpServletRequest request
    		) throws RepositoryException {

		Long userId = Util.getSessionUserId(request);
    	log.info("user id: "+userId);
        if (userId == null) return "components/logout.ftl";
        
		new PhoneFormValidator().validate(profileForm, result);
        if (result.hasErrors()) return formPage;
 
        
        Message msg =  registrationService.updateProfilePhone(userId, profileForm.getPhone());
        if (msg.getStatus().equals(BaseConfig.MSG_ERROR)) {
   			result.reject(msg.getMsg(), msg.getMsg());
        	return formPage;
        }    

        Message msg1 =  registrationService.newUserActive (userId);
        if (msg1.getStatus().equals(BaseConfig.MSG_ERROR)) {
   			result.reject(msg1.getMsg(), msg1.getMsg());
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
