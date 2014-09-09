package com.coin.components;


import info.magnolia.cms.core.Content;
import info.magnolia.module.blossom.annotation.Template;
import info.magnolia.module.blossom.annotation.TemplateDescription;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coin.areas.Main;
import com.coin.model.User;

@Template(title = "Register Success", id = "coinstore-blossom:components/registerSuccess")
@TemplateDescription("Register Success")
@Controller
@Main
public class RegisterSuccess {
  

    @RequestMapping("/registerSuccess")
    public String render(ModelMap model, HttpSession session, HttpServletRequest request, Content content) {
    	User user = new User();
    	user.setEmail("test");
    	model.put("user",user);
        return "components/registerSuccess.ftl";
    }

   
}


