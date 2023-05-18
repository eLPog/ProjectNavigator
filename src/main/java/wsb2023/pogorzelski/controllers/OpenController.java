package wsb2023.pogorzelski.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Random;

@Controller
public class OpenController {

    @GetMapping("/contact")
    public String contact(){
        return "open/contact";
    }

    @GetMapping("/")
    public String mainPage(){
        return "layouts/basic";
    }

    @GetMapping("/forbidden")
    public String forbidden(){
        return "open/forbidden";
    }

    @GetMapping("/test")
    public ModelAndView test(){
        ModelAndView modelAndView = new ModelAndView("open/test");
        Integer i = new Random().nextInt();
        modelAndView.addObject("random",i);
        return modelAndView;
    }
}
