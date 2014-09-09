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
 
@Template(title = "Profile Updated", id = "coinstore-blossom:components/profileUpdated")
@TemplateDescription("Profile Updated")
@Controller
@Main
public class ProfileUpdated {
  

    @RequestMapping("/profileUpdated")
    public String render(ModelMap model, HttpSession session, HttpServletRequest request, Content content) {

        
        return "components/profileUpdated.ftl";
    }

   
}


