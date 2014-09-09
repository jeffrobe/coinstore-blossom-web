package com.coin.components;


import info.magnolia.module.blossom.annotation.TabFactory;
import info.magnolia.module.blossom.annotation.Template;
import info.magnolia.module.blossom.annotation.TemplateDescription;
import info.magnolia.ui.form.config.TabBuilder;
import info.magnolia.ui.framework.config.UiConfig;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.coin.areas.Main;
import com.coin.common.BaseConfig;
import com.coin.dao.FundingsourceDao;
import com.coin.dao.TrialdepositDao;
import com.coin.model.Fundingsource;
import com.coin.model.Message;
import com.coin.model.Trialdeposit;
import com.coin.web.common.Util;
import com.coin.web.forms.FundingsourceForm;
import com.coin.web.service.FundingsourceService;
 
@Controller
@Template(title = "Fundingsource Action", id = "coinstore-blossom:components/fundingsourceAction")
@TemplateDescription("A fundingsource action ...")
@Main
public class FundingsourceActionComponent {
	private static final Logger log = Logger.getLogger(FundingsourceActionComponent.class);
	@Autowired
	private FundingsourceDao fundingsourceDao;

	@Autowired
	private TrialdepositDao trialdepositDao;

	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	FundingsourceService fundingsourceService;
	 
	@RequestMapping(value = "/fundingsourceAction", method = RequestMethod.GET)
    public String viewForm( ModelMap model,   Node content,
    		@RequestParam (value = "fundingsourceId", required = false) Long fundingsourceId, 
    		@RequestParam (value = "trialdepositId", required = false) Long trialdepositId, 
    		@RequestParam (value = "deposit1", required = false) BigDecimal deposit1, 
    		@RequestParam (value = "deposit2", required = false) BigDecimal deposit2, 
    		@RequestParam (value = "userId", required = false) Long userIdGet, 
    		@RequestParam (value = "action", required = false) String action, 
    		HttpServletRequest request, @ModelAttribute FundingsourceForm fundingsourceForm) throws RepositoryException {
		
		boolean hasError=false;
		List<String> errors = new ArrayList<String> ();

		Long userId = Util.getLoggedUserId();

		if (userId==null && userIdGet!= null && userIdGet>0)  userId=userIdGet;
		
    	log.info("user id: "+userId);
    	model.addAttribute("userId", userId);

    	if (userId == null) {
    		log.error("no user id");
    		return "components/logout.ftl";
    	}
    	
    	if (fundingsourceId == null) {
    		log.error("fundingsource id is null");
    		return "components/error.ftl";    		
    	}
    	

    	if (action != null && action.equals("resend")) {
    		log.debug("resend deposits");
    		Message msg = fundingsourceService.createTrialDeposit(fundingsourceId);
    		log.debug(msg);
    		if (!msg.getMsg().equals(BaseConfig.MSG_SUCCESS)) {
				errors.add(messageSource.getMessage(msg.getMsg(), null, msg.getMsg(), LocaleContextHolder.getLocale()));
				hasError=true;
    		}
    		else return "website:" + content.getProperty("successPage").getString(); 
    	}
    	
    	
    	if (action != null && action.equals("verify")) {
    		log.debug("verify deposits");
    		Message msg = fundingsourceService.confirmTrialDeposit( trialdepositId,   deposit1,   deposit2);
    		log.debug(msg);
    		if (!msg.getMsg().equals(BaseConfig.MSG_SUCCESS)) {
				errors.add(messageSource.getMessage(msg.getMsg(), null, msg.getMsg(), LocaleContextHolder.getLocale()));
				hasError=true;
    		}
    		
    	}
    	
    	
    	Fundingsource fundingsource = fundingsourceDao.findById(fundingsourceId);
    	if (fundingsource == null) {
    		log.error("fundingsource is null");
    		return "redirect: components/error.ftl";
    	}
    	    
    	List<Trialdeposit> trialdepositList = trialdepositDao.findByFundingsourceId(fundingsourceId);
    	model.addAttribute("trialdepositList", trialdepositList);
    	model.addAttribute("fundingsource", fundingsource);
    	model.addAttribute("hasError", hasError);
    	model.addAttribute("errors", errors);
    	
        return "components/fundingsource.ftl";
      
    }
 
    @TabFactory("Content")
    public void contentTab(UiConfig cfg, TabBuilder tab) {
    	tab.fields(
        	cfg.fields.pageLink("successPage").label("Success page")
        );
    }
    
    
   
    
}
