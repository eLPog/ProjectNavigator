package wsb2023.pogorzelski.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import wsb2023.pogorzelski.services.AuthorityService;

@Controller
@RequestMapping("/roles")
@AllArgsConstructor
@EnableWebSecurity
public class AuthorityController {

final private AuthorityService authorityService;

    @PostMapping("/{userId}")
    public String addRoleToUser(@PathVariable Long userId, @RequestParam("roleId") Long roleId){
    authorityService.addRoleToUser(userId,roleId);
    return "redirect:/person/"+userId;
    };

    @GetMapping("/{userId}/{roleName}")
    public String removeRoleFromUser(@PathVariable Long userId, @PathVariable("roleName") String roleName){
        authorityService.removeRoleFromUser(userId,roleName);

        return "redirect:/person/"+userId;
    };

}
