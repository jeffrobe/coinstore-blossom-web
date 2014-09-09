package com.coin.pages;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coin.areas.Admin;
 

import com.coin.web.common.Config;
import com.coin.components.ui.TextComponent;
import com.coin.components.ui.TwoColumnComponent;

import info.magnolia.cms.core.MgnlNodeType;
import info.magnolia.jcr.util.NodeUtil;
import info.magnolia.jcr.util.PropertyUtil;
import info.magnolia.module.blossom.annotation.Area;
import info.magnolia.module.blossom.annotation.AvailableComponentClasses;
import info.magnolia.module.blossom.annotation.Inherits;
import info.magnolia.module.blossom.annotation.TabFactory;
import info.magnolia.module.blossom.annotation.Template;
import info.magnolia.module.blossom.annotation.TernaryBoolean;
import info.magnolia.ui.form.config.TabBuilder;
import info.magnolia.ui.framework.config.UiConfig;

 
@Controller
@Template(title = "Admin Coin Blossom", id = "coinstore-blossom:pages/admin")
public class AdminTemplate {

    @Area("main")
    @Controller
    @AvailableComponentClasses({Admin.class})
    public static class MainArea {

        @RequestMapping("/adminTemplate/main")
        public String render() {
            return "areas/main.ftl";
        }
    }
 
    @RequestMapping("/adminTemplate")
    public String render(Node page, ModelMap model) throws RepositoryException {

        Map<String, String> navigation = new LinkedHashMap<String, String>();
        for (Node node : NodeUtil.getNodes(page.getSession().getNode("/"+Config.adminHome), MgnlNodeType.NT_PAGE)) {
            if (!PropertyUtil.getBoolean(node, "hideInNavigation", false)) {
                navigation.put(node.getPath(), PropertyUtil.getString(node, "title", ""));
            }
        }
        
 
        model.put("contextBase", Config.adminHome);
        model.put("navigation", navigation);

        return "pages/admin.ftl";
    }


}
