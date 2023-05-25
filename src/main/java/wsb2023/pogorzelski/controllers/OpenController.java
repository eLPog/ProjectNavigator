package wsb2023.pogorzelski.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import wsb2023.pogorzelski.services.UtilService;

import java.util.Random;

@Controller
@AllArgsConstructor
public class OpenController {

 UtilService utilService;
    @GetMapping("/contact")
    public String contact(){
        return "open/contact";
    }



    @GetMapping("/test")
    public ModelAndView test(){
        ModelAndView modelAndView = new ModelAndView("open/test");
        Integer i = new Random().nextInt();
        String username = utilService.checkLoggedUserName();
        modelAndView.addObject("random",i);
        return modelAndView;
    }

}
