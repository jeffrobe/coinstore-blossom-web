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
@Template(title = "Forgot Password Success", id = "coinstore-blossom:components/forgotpasswordSuccess")
@TemplateDescription("A forgotpassword success ...")
@Main
public class ForgotpasswordSuccessComponent {

 

    @RequestMapping("/forgotpasswordSuccesss")
    public String render(ModelMap model, HttpSession session, HttpServletRequest request, Content content) {

        model.put("email", "email");

        return "components/forgotpasswordSuccess.ftl";
    }
    
}
