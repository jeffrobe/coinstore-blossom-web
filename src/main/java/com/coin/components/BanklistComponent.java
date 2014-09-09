package com.coin.components;


import info.magnolia.cms.core.Content;
import info.magnolia.module.blossom.annotation.Template;
import info.magnolia.module.blossom.annotation.TemplateDescription;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coin.areas.Admin;
import com.coin.dao.BankDao;
import com.coin.model.Bank;

//list of users
@Template(title = "Bank List", id = "coinstore-blossom:components/banklist")
@TemplateDescription("List of banks")
@Controller
@Admin
public class BanklistComponent {
	private static final Logger log = Logger.getLogger(BanklistComponent.class);
	@Autowired
	private BankDao bankDao;

    @RequestMapping("/banklist")
    public String render(ModelMap model, HttpSession session, HttpServletRequest request, Content content) {
    	List<Bank> lst = bankDao.list();
    	
        model.put("banks", lst);
        log.info("banks: "+lst.toString());

        return "components/banklist.ftl";
    }

   
}


