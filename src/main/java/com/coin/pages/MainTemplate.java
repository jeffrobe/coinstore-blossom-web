package com.coin.pages;


import java.util.LinkedHashMap;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coin.areas.Main;
import com.coin.areas.Promo;
import com.coin.web.common.Config;
import com.coin.web.service.IAuthenticationFacade;
import com.coin.components.ui.TextComponent;
import com.coin.components.ui.TwoColumnComponent;

import info.magnolia.cms.core.MgnlNodeType;
import info.magnolia.jcr.util.NodeUtil;
import info.magnolia.jcr.util.PropertyUtil;
import info.magnolia.module.blossom.annotation.Area;
import info.magnolia.module.blossom.annotation.AvailableComponentClasses;
import info.magnolia.module.blossom.annotation.DialogFactory;
import info.magnolia.module.blossom.annotation.Inherits;
import info.magnolia.module.blossom.annotation.TabFactory;
import info.magnolia.module.blossom.annotation.Template;
import info.magnolia.module.blossom.annotation.TernaryBoolean;
import info.magnolia.ui.dialog.config.DialogBuilder;
import info.magnolia.ui.form.config.TabBuilder;
import info.magnolia.ui.framework.config.UiConfig;


/**
 * Template with two columns, a main content area and a right side column.
 */
@Controller
@Template(title = "Main Coin Blossom", id = "coinstore-blossom:pages/main")
public class MainTemplate {
	@Autowired
	private IAuthenticationFacade authenticationFacade;
	
    /**
     * Main area.
     */
    @Area("main")
    @Controller
    @AvailableComponentClasses({Main.class, TwoColumnComponent.class})
    public static class MainArea {

        @RequestMapping("/mainTemplate/main")
        public String render() {
            return "areas/main.ftl";
        }
    }

    /**
     * Promos area, uses the {@link Promo} component category annotation to specify which components are available.
     */
    @Controller
    @Area(value = "promos", title = "Promos", optional = TernaryBoolean.TRUE)
    @Inherits
    @AvailableComponentClasses({Promo.class})
    public static class PromosArea {

        @RequestMapping("/mainTemplate/promos")
        public String render() {
            return "areas/promos.ftl";
        }
    }

    @RequestMapping("/mainTemplate")
    public String render(Node page, ModelMap model) throws RepositoryException {

        Map<String, String> navigation = new LinkedHashMap<String, String>();
        for (Node node : NodeUtil.getNodes(page.getSession().getNode("/"+Config.webHome), MgnlNodeType.NT_PAGE)) {
            if (!PropertyUtil.getBoolean(node, "hideInNavigation", false)) {
                navigation.put(node.getPath(), PropertyUtil.getString(node, "title", ""));
            }
        }
        
        model.put("userId", authenticationFacade.getUserId());
        model.put("isLoggedIn", authenticationFacade.isLoggedIn());
        String userDisplayName="n/a";
        if (authenticationFacade.getUserDisplayName()!=null) userDisplayName = authenticationFacade.getUserDisplayName();
        model.put("userDisplayName", userDisplayName );
        model.put("memberDirectory", Config.memberDirectory);
        model.put("contextBase", Config.webHome);
        
        model.put("navigation", navigation);

        return "pages/main.ftl";
    }

    /**
     * Dialog factory associated to the Main page settings.
     *
     * @param dialog
     */
    
    @DialogFactory("main-dialog")
    public void homeDialog(UiConfig cfg, TabBuilder tab) {
    	
        tab.fields(
            	cfg.fields.text("title").label("Main page settings"),
                cfg.fields.checkbox ("hideInNavigation").label("Hide in navigation").description("Check this box to hide this page in navigation").buttonLabel(""),
                cfg.fields.checkbox("requireLogin").label("require login").description("Check this box to require login").buttonLabel("")
            );
    	
    }
    
    @TabFactory("Content")
    public void contentTab(UiConfig cfg, TabBuilder tab) {
        tab.fields(
        	cfg.fields.text("title").label("Title"),
            cfg.fields.checkbox("hideInNavigation").label("Hide in navigation").description("Check this box to hide this page in navigation").buttonLabel(""),
            cfg.fields.checkbox("requireLogin").label("require login").description("Check this box to require login").buttonLabel("")
        );
    }
}
