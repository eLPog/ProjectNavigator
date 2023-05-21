package wsb2023.pogorzelski.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Secured("USER")
public class GeneralController {

    @GetMapping("/")
    public String mainPage(){
        return "layouts/basic";
    }

    @GetMapping("/forbidden")
    public String forbidden(){
        return "open/forbidden";
    }
}
