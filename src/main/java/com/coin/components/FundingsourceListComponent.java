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
import com.coin.dao.FundingsourceDao;
import com.coin.web.common.Util;
import com.coin.model.Fundingsource;
 
@Template(title = "Fundingsource List", id = "coinstore-blossom:components/fundingsourceList")
@TemplateDescription("List of Fundingsource")
@Controller
@Main

@SessionAttributes("fundingsources")
public class FundingsourceListComponent {
  
	@Autowired
	private FundingsourceDao fundingsourceDao;

    @RequestMapping("/fundingsourceList")
    public String render(ModelMap model, HttpSession session, 
    		HttpServletRequest request, Content content) {

    	Long userId = Util.getLoggedUserId();
    	
        return "components/fundingsourceList.ftl";
    }

	@ModelAttribute("fundingsources")
	public List<Fundingsource> getFundingsources () {
		Long userId = Util.getLoggedUserId();
		List<Fundingsource> lst = fundingsourceDao.findByUserId(userId);
    		
	    return lst;
	}
   
}


