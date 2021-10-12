package chris.webapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    /**
     * Shows the view for the root URI of the web app.
     */
    @GetMapping("")
    public String home() {
        return "home";  // view name
    }
}