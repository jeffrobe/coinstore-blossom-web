  
package  com.coin.components.ui;

import info.magnolia.module.blossom.annotation.Area;
import info.magnolia.module.blossom.annotation.AvailableComponentClasses;
import info.magnolia.module.blossom.annotation.Template;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coin.areas.Admin;
import com.coin.areas.Main;

/**
 * Component with two areas arranged as columns.
 */
@Controller
@Template(id="coinstore-blossom:components/twoColumn", title="Two column layout")
@Main
@Admin
public class TwoColumnComponent {

    /**
     * Left column.
     */
    @Area("left")
    @Controller
    @AvailableComponentClasses({TextComponent.class})
    public static class LeftArea {

        @RequestMapping("/twoColumn/left")
        public String render() {
            return "areas/leftArea.ftl";
        }
    }

    /**
     * Right column.
     */
    @Area("right")
    @Controller
    @AvailableComponentClasses({TextComponent.class})
    public static class RightArea {

        @RequestMapping("/twoColumn/right")
        public String render() {
            return "areas/rightArea.ftl";
        }
    }

    @RequestMapping("/twoColumn")
    public String render() {
        return "areas/twoColumns.ftl";
    }
}
