package com.coin.components;


import info.magnolia.module.blossom.annotation.Template;
import info.magnolia.module.blossom.annotation.TemplateDescription;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.coin.areas.Main;
import com.coin.web.common.Config;
import com.coin.dao.UserDao;
import com.coin.spring.security.CustomWebAuthenticationDetails;
import com.coin.web.forms.LoginFormValidator;
import com.coin.web.forms.ProfileForm;
import com.coin.web.service.RegistrationService;
 
@Controller
@Template(title = "Login Form", id = "coinstore-blossom:components/loginForm")
@TemplateDescription("A login form ...")
@Main
public class LoginFormComponent  extends BaseComponent {
	private String formPage = "components/loginForm.ftl";

	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	RegistrationService registrationService;

	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authenticationManager;
	
	LoginFormComponent() {
	}
	
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String viewForm(ModelMap model, @ModelAttribute ProfileForm loginForm) {
    	return formPage;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String handleSubmit(ModelMap model, @ModelAttribute ProfileForm loginForm,
    		BindingResult result, Node content,  HttpServletRequest request
    	) throws RepositoryException  {

    	
		new LoginFormValidator().validate(loginForm, result);
		if (getrequireToken() && ! getseperateTokenAuth() && (loginForm.getToken()==null || loginForm.getToken().isEmpty()) )
			result.rejectValue("token", "token.required","token.required");
	
    	log.error("test logoin: "+result.toString());
    	
        if (result.hasErrors()) return formPage;

        try
        {
        	CustomWebAuthenticationDetails details = new CustomWebAuthenticationDetails(request);
        	UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginForm.getEmail(), loginForm.getPassword());
		
			token.setDetails(details);
            Authentication auth = authenticationManager.authenticate(token);
           
            SecurityContextHolder.getContext().setAuthentication(auth);

            HttpSession session = request.getSession();
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

            return Config.memberRedirect;
        }
        catch (AuthenticationException e)
        {
            SecurityContextHolder.getContext().setAuthentication(null);
            String errorMessage = e.getLocalizedMessage();
            log.error("login error: "+errorMessage+ " "+ e.getMessage());
        	result.reject (errorMessage,errorMessage);
        	return formPage;
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
