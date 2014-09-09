package com.coin.components;


import info.magnolia.module.blossom.annotation.Template;
import info.magnolia.module.blossom.annotation.TemplateDescription;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.coin.areas.Main;
import com.coin.web.common.Config;
import com.coin.dao.NotificationtypeDao;
import com.coin.dao.UserDao;
import com.coin.model.Message;
import com.coin.model.Notificationtype;
import com.coin.model.User;
import com.coin.web.forms.ProfileForm;
import com.coin.web.forms.ProfileFormValidator;
import com.coin.web.service.IAuthenticationFacade;
import com.coin.web.service.RegistrationService;
 
@Controller
@Template(title = "Profile Form", id = "coinstore-blossom:components/profileForm")
@TemplateDescription("A profile form ...")
@Main
public class ProfileFormComponent  {
	private static final Logger log = Logger.getLogger(ProfileFormComponent.class);
	private static final String formPage = "components/profileForm.ftl";
 
	@Autowired
	private IAuthenticationFacade authenticationFacade;
	
	@Autowired
	private NotificationtypeDao notificationtypeDao;

	@Autowired
	private UserDao userDao;
	
	@Autowired
	RegistrationService registrationService;

	@RequestMapping(value = "/profileForm", method = RequestMethod.GET)
    public String viewForm( Model model, 
    		HttpServletRequest request, @ModelAttribute ProfileForm profileForm) {
		
    	Long userId = authenticationFacade.getUserId();  	 
    	User user = userDao.getUserObj(userId);
    	
		Set<Notificationtype> ntypes = user.getNotificationtypes();
		if (ntypes!=null) {
			List<String> l = new ArrayList<String>();
			for (Notificationtype n : ntypes) l.add(n.getId().toString());
			
			//String[] notifIds = new String[l.size()];
			//notifIds = l.toArray(notifIds);
			
			String[] notifIds = l.toArray(new String[l.size()]);
			
			profileForm.setNotificationtypestr(notifIds);
		}
		else profileForm.setNotificationtypestr(null);
		 	
    	profileForm.setId(user.getId());
    	profileForm.setEmail(user.getEmail());
    	 
    	profileForm.setFirstname(user.getFirstname());
    	profileForm.setLastname(user.getLastname());
    	profileForm.setPhone(user.getPhone());
    	profileForm.setDisplayname(user.getDisplayname());
    	profileForm.setAuthyid(user.getAuthyid());
        return formPage;
      
    }

	@RequestMapping(value = "/profileForm", method = RequestMethod.POST)
    public String handleSubmit(@ModelAttribute ProfileForm profileForm, BindingResult result, Node content, 
    		HttpServletRequest request
    		) throws RepositoryException {

		
		new ProfileFormValidator().validate(profileForm, result);		
        if (result.hasErrors()) return formPage;

    	Long userId = authenticationFacade.getUserId();  	 
    	User user = userDao.getUserObj(userId);

        Set<Notificationtype> nt = new HashSet<Notificationtype>();
    	String[] typesStr = profileForm.getNotificationtypestr();
    	if (typesStr!=null) {
	    	for (String s : typesStr) {
	        	Notificationtype e = notificationtypeDao.findById(new Long(s));
	        	nt.add(e);
	    	}
    	}
    	
    	user.setNotificationtypes(nt);
    	user.setFirstname(profileForm.getFirstname());
    	user.setDisplayname(profileForm.getDisplayname());
    	user.setLastname(profileForm.getLastname());
    	user.setPhone(profileForm.getPhone());
    	user.setAuthyid(profileForm.getAuthyid());
      	
        Message msg =  registrationService.updateProfile( user );
        if (msg.getStatus().equals(Config.MSG_ERROR)) {
        	result.reject(msg.getMsg(),msg.getMsg());
        	return formPage;
        }    

       	return Config.memberRedirect;
    }
 
    
    @ModelAttribute("ntypes")
    public List<Notificationtype> getNtypes() {
    	List<Notificationtype> items = notificationtypeDao.list();   
    	return items;
    }

 
    
    @ModelAttribute("ntypesHash")
    public Map<String, String> getNtypesHash() {
    	Map<String, String> m = new HashMap<String, String>();
    	List<Notificationtype> items = notificationtypeDao.list();   
		
    	for (Notificationtype item : items)
    		m.put(item.getId().toString(), item.getName());

    	return m;
    }
 
}
