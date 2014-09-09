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
import com.coin.dao.BtcaddressDao;
import com.coin.dao.WalletDao;
import com.coin.web.forms.BtcaddressForm;
import com.coin.web.forms.BtcaddressFormValidator;
import com.coin.model.Message;
import com.coin.model.Btcaddress;
import com.coin.model.Wallet;
import com.coin.web.service.IAuthenticationFacade;
import com.coin.web.service.WalletService;
 
@Controller
@Template(title = "BtcaddressForm Form", id = "coinstore-blossom:components/btcaddressForm)")
@TemplateDescription("A BtcaddressForm form ...")
@Main
public class BtcaddressFormComponent {
	private static final Logger log = Logger.getLogger(BtcaddressFormComponent.class);

	private final String formPage = "components/btcaddressForm.ftl";
	
	@Autowired
	private IAuthenticationFacade authenticationFacade;
	
	@Autowired
	private BtcaddressDao btcaddressDao;

	@Autowired
	private WalletDao walletDao;

	@RequestMapping(value = "/btcaddressForm", method = RequestMethod.GET)
    public String viewForm( ModelMap model, 
    		@RequestParam (value = "id", required = false) Long id, 
    		HttpServletRequest request, @ModelAttribute BtcaddressForm btcaddressForm) {
		
    	if (id != null) {
    		Btcaddress btcaddress = btcaddressDao.findById(id);
    		btcaddressForm.setId(id);
    		btcaddressForm.setWalletId (btcaddress.getWalletId());
    		btcaddressForm.setPrivatekey(btcaddress.getPrivatekey());
    		btcaddressForm.setPublickey(btcaddress.getPublickey());
            btcaddress.setStatus(Config.statusActive);

    	}
    	
        return formPage;
      
    }

	@RequestMapping(value = "/btcaddressForm", method = RequestMethod.POST)
    public String handleSubmit(ModelMap model, @ModelAttribute BtcaddressForm btcaddressForm,
    		BindingResult result, Node content,  HttpServletRequest request
    		) throws RepositoryException {
 
    	Long userId = authenticationFacade.getUserId();  	 

        
		new BtcaddressFormValidator().validate(btcaddressForm, result);
	 
        if (result.hasErrors()) return formPage;

        Btcaddress btcaddress = new Btcaddress();
        btcaddress.setWalletId(btcaddressForm.getWalletId());
        btcaddress.setPrivatekey(btcaddressForm.getPrivatekey()); 
        btcaddress.setPublickey(btcaddressForm.getPublickey()); 
        btcaddress.setUserId(userId);
        btcaddress.setStatus(Config.statusActive);
		log.debug("walletForm id: "+ btcaddress.getId());
		
		 
		if (btcaddressForm.getId() != null && btcaddressForm.getId()>0) {
			btcaddress.setId(btcaddressForm.getId());
			btcaddressDao.update(btcaddress);
			
    	}
		else {
			btcaddressDao.save(btcaddress);
		}

	   	return Config.memberRedirect;

    }
 
    
    @ModelAttribute("walletHash")
    public Map<String,String> getWallettypes( ) {
    	Map<String,String> map = new LinkedHashMap<String,String>();
    	
    	if (Config.SelectOptionDefaultEntry) map.put(Config.SelectOptionDefaultValue, Config.SelectOptionDefaultLabel);

    	List<Wallet> wts = walletDao.listActive();
    	for (Wallet w: wts) map.put(w.getId().toString(), w.getName());
    	
    	return map;
    }
    
    
}
