package wsb2023.pogorzelski.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
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
    @Secured("ROLE_MANAGE_USERS")
    public String addRoleToUser(@PathVariable Long userId, @RequestParam("roleId") Long roleId){
    authorityService.addRoleToUser(userId,roleId);
    return "redirect:/person/"+userId;
    };

    @GetMapping("/{userId}/{roleName}")
    @Secured("ROLE_MANAGE_USERS")
    public String removeRoleFromUser(@PathVariable Long userId, @PathVariable("roleName") String roleName){
        authorityService.removeRoleFromUser(userId,roleName);

        return "redirect:/person/"+userId;
    };

}
