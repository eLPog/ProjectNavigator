package wsb2023.pogorzelski.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OpenController {

    @GetMapping("/contact")
    public String contact(){
        return "open/contact";
    }

    @GetMapping("/")
    public String mainPage(){
        return "open/main";
    }
}
