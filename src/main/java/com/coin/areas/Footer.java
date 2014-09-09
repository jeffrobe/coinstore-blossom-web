package com.coin.areas;

import info.magnolia.module.blossom.annotation.ComponentCategory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import info.magnolia.module.blossom.annotation.Area;
import info.magnolia.module.blossom.annotation.DialogFactory;
import info.magnolia.module.blossom.annotation.TabFactory;
 
 

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
 
@Area(title = "footerArea", value = "Footer Area", dialog = "footer-dialog")
@DialogFactory("footer-dialog")
@Controller
public class Footer {
    /**
     * Process the requests on URL /footerArea
     *
     * @return
     */
    @RequestMapping("/footerArea")
    public ModelAndView handleRequest() {
        return new ModelAndView();
    }
 
    /**
     * Tabs of the Header Paragraph.
     *
     * @param tab
     
    @TabFactory("Header")
    public void content(TabBuilder tab) {
        tab.addStatic("Header paragraph");
        // Logo image
        tab.addFile("logo", "Logo", "Logo Image (about 120 x 100 px)");
        // Header text
        tab.addEdit("text", "Header Text", "Header Text (about 60 characters)");
    }
    */
}