package com.coin.components.admin;


import info.magnolia.cms.core.Content;
import info.magnolia.module.blossom.annotation.Template;
import info.magnolia.module.blossom.annotation.TemplateDescription;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coin.areas.Admin;
import com.coin.dao.RoleDao;
import com.coin.model.Role;

//list of users
@Template(title = "Roles List", id = "coinstore-blossom:components/roleList")
@TemplateDescription("List of roles")
@Controller
@Admin
public class RoleListComponent {
  
	@Autowired
	private RoleDao roleDao;

    @RequestMapping("/roleList")
    public String render(ModelMap model, HttpSession session, HttpServletRequest request, Content content) {
    	List<Role> lst = roleDao.list();
    	
        model.put("roles", lst);

        return "components/roleList.ftl";
    }

   
}


