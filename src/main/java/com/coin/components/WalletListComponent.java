package com.coin.components;


import info.magnolia.cms.core.Content;
import info.magnolia.module.blossom.annotation.Template;
import info.magnolia.module.blossom.annotation.TemplateDescription;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.coin.areas.Main;
import com.coin.dao.WalletDao;
import com.coin.web.common.Util;
import com.coin.model.Wallet;;
 
@Template(title = "Fundingsource List", id = "coinstore-blossom:components/walletList")
@TemplateDescription("List of Fundingsource")
@Controller
@Main

@SessionAttributes("fundingsources")
public class WalletListComponent {
  
	@Autowired
	private WalletDao walletDao;

    @RequestMapping("/walletList")
    public String render(ModelMap model, HttpSession session, 
    		HttpServletRequest request, Content content) {

    	Long userId = Util.getLoggedUserId();
    	
        return "components/walletList.ftl";
    }

	@ModelAttribute("wallets")
	public List<Wallet> getWallets () {
		Long userId = Util.getLoggedUserId();
		List<Wallet> lst =  walletDao.findByUserId(userId);
    		
	    return lst;
	}
   
}


