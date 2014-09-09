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
import com.coin.dao.NotificationtypeDao;
import com.coin.model.Notificationtype;

@Template(title = "Notificationtype List", id = "coinstore-blossom:components/notificationtypeList")
@TemplateDescription("List of notificaiton types")
@Controller
@Admin
public class NotificationtypeListComponent {
  
	@Autowired
	private NotificationtypeDao notificationtypeDao;

    @RequestMapping("/notificationtypeList")
    public String render(ModelMap model, HttpSession session, HttpServletRequest request, Content content) {
    	List<Notificationtype> lst = notificationtypeDao.list();
    	
        model.put("notificationtypes", lst);

        return "components/notificationtypeList.ftl";
    }

   
}


