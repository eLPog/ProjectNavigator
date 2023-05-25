package wsb2023.pogorzelski.controllers;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import wsb2023.pogorzelski.services.AuthorityService;

@Controller
@RequestMapping("/roles")
@AllArgsConstructor
@EnableWebSecurity
public class AuthorityController {

AuthorityService authorityService;

    @PostMapping("/{userId}")
    public String addRoleToUser(@PathVariable Long userId, @RequestParam("roleId") Long roleId){
    authorityService.addRoleToUser(userId,roleId);
    return "redirect:../person/all";
    };

}
