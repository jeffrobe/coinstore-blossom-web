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
import com.coin.dao.UserDao;
import com.coin.model.User;

//list of users
@Template(title = "UserList", id = "coinstore-blossom:components/userlist")
@TemplateDescription("List of users")
@Controller
@Admin
public class UserlistComponent {
  
	@Autowired
	private UserDao userDao;

    @RequestMapping("/userlist")
    public String render(ModelMap model, HttpSession session, HttpServletRequest request, Content content) {
    	List<User> lst = userDao.list();
    	
        model.put("users", lst);

        return "components/userlist.ftl";
    }

   
}


