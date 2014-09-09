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
import com.coin.dao.NotificationDao;
import com.coin.model.Notification;

//list of users
@Template(title = "Notification List", id = "coinstore-blossom:components/notificationList")
@TemplateDescription("List of notificaitons")
@Controller
@Admin
public class NotificationListComponent {
  
	@Autowired
	private NotificationDao notificationDao;

    @RequestMapping("/notificationList")
    public String render(ModelMap model, HttpSession session, HttpServletRequest request, Content content) {
    	List<Notification> lst = notificationDao.list();
    	
        model.put("notifications", lst);

        return "components/notificationList.ftl";
    }

   
}


