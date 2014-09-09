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
 
 
@Controller
@Template(title = "Reset Password Success", id = "coinstore-blossom:components/resetPasswordSuccess")
@TemplateDescription("A reset password success ...")
@Main
public class ResetpasswordSuccessComponent {

    @RequestMapping("/resetPasswordSuccess")
    public String render(ModelMap model, HttpSession session, HttpServletRequest request, Content content) {

        model.put("email", "email");

        return "components/resetPasswordSuccess.ftl";
    }
    
}
