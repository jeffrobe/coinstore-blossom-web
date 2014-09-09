package com.coin.components;


import info.magnolia.module.blossom.annotation.Template;
import info.magnolia.module.blossom.annotation.TemplateDescription;

import java.util.LinkedHashMap;
import java.util.List;
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
import com.coin.web.common.Config;
import com.coin.dao.WalletDao;
import com.coin.dao.WallettypeDao;
import com.coin.web.forms.WalletForm;
import com.coin.web.forms.WalletFormValidator;
import com.coin.model.Message;
import com.coin.model.Wallet;
import com.coin.model.Wallettype;
import com.coin.web.service.IAuthenticationFacade;
import com.coin.web.service.WalletService;
 
@Controller
@Template(title = "Wallet Form", id = "coinstore-blossom:components/walletForm")
@TemplateDescription("A fundingsource form ...")
@Main
public class WalletFormComponent {
	private static final Logger log = Logger.getLogger(FundingsourceFormComponent.class);

	private final String formPage = "components/walletForm.ftl";
	
	@Autowired
	private IAuthenticationFacade authenticationFacade;
	
	@Autowired
	private WalletDao walletDao;

	@Autowired
	private WallettypeDao wallettypeDao;
	
	@Autowired
	WalletService walletService;
	 
	@RequestMapping(value = "/walletForm", method = RequestMethod.GET)
    public String viewForm( ModelMap model, 
    		@RequestParam (value = "id", required = false) Long id, 
    		HttpServletRequest request, @ModelAttribute WalletForm walletForm) {
		
	    
    	
    	if (id != null) {
    		Wallet wallet = walletDao.findById(id);
    		walletForm.setId(id);
    		walletForm.setTypeId(wallet.getTypeId());
    		walletForm.setName(wallet.getName());
     
    	}
    	
        return formPage;
      
    }

	@RequestMapping(value = "/walletForm", method = RequestMethod.POST)
    public String handleSubmit(ModelMap model, @ModelAttribute WalletForm walletForm,
    		BindingResult result, Node content,  HttpServletRequest request
    		) throws RepositoryException {
 
    	Long userId = authenticationFacade.getUserId();  	 

        
		new WalletFormValidator().validate(walletForm, result);
	 
        if (result.hasErrors()) return "components/walletForm.ftl";

		Wallet wallet = new Wallet();
		wallet.setTypeId(walletForm.getTypeId());
		wallet.setName(walletForm.getName()); 
		wallet.setUserId(userId);
		log.debug("walletForm id: "+ walletForm.getId());
		
		Message msg=null;
		if (walletForm.getId() != null && walletForm.getId()>0) {
			wallet.setId(walletForm.getId());
		 
			msg = walletService.updateWallet(wallet);
    	}
		else {
		
			msg = walletService.newWallet(wallet);
		}

		log.debug(msg);
        if (msg != null && msg.getStatus().equals(Config.MSG_SUCCESS))
           	return Config.memberRedirect;

        else {
        	if (msg.getMsg().equals("name_exist")) result.rejectValue("name", "account.name.inuse", "account.name.inuse");
        	result.reject(msg.getMsg());
        	 
        	return formPage;
        }
        	
    }
 
    
    @ModelAttribute("wallettypesHash")
    public Map<String,String> getWallettypes( ) {
    	Map<String,String> map = new LinkedHashMap<String,String>();
    	
    	if (Config.SelectOptionDefaultEntry) map.put(Config.SelectOptionDefaultValue, Config.SelectOptionDefaultLabel);

    	List<Wallettype> wts = wallettypeDao.listActive();
    	for (Wallettype w: wts) map.put(w.getId().toString(), w.getName());
    	
    	return map;
    }
    
    
}
