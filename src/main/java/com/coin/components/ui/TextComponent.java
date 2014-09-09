  
package  com.coin.components.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import info.magnolia.module.blossom.annotation.TabFactory;
import info.magnolia.module.blossom.annotation.Template;
import info.magnolia.module.blossom.annotation.TemplateDescription;
import info.magnolia.ui.form.config.TabBuilder;
import info.magnolia.ui.framework.config.UiConfig;

import com.coin.areas.Admin;
import com.coin.areas.Main;
import com.coin.areas.Promo;

/**
 * Simple component for adding text to a page.
 */

@Controller
@Template(title = "Text", id = "coinstore-blossom:components/text")
@TemplateDescription("Simple text block")
@Promo
@Main
@Admin
public class TextComponent {

    @RequestMapping("/text")
    public String render() {
        return "areas/text.ftl";
    }

    @TabFactory("Content")
    public void contentTab(UiConfig cfg, TabBuilder tab) {
        tab.fields(
              cfg.fields.text("heading").label("Heading"),
              cfg.fields.richText("body").label("Text body")
        );
    }
}

