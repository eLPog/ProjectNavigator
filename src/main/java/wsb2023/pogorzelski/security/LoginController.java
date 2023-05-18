package wsb2023.pogorzelski.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String index(){
        return "security/login";
    }

    @GetMapping("/logout")
    public String logout(){
        return "security/login";
    }
}
