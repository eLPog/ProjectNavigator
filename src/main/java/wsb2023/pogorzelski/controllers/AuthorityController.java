package wsb2023.pogorzelski.controllers;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import wsb2023.pogorzelski.models.Person;
import wsb2023.pogorzelski.models.Rights;
import wsb2023.pogorzelski.services.AuthorityService;
import wsb2023.pogorzelski.services.PersonService;

@Controller
@RequestMapping("/roles")
@AllArgsConstructor
@EnableWebSecurity
public class AuthorityController {

AuthorityService authorityService;
PersonService personService;

    @PostMapping("/{userId}")
    public String addRoleToUser(@PathVariable Long userId, @RequestParam("roleId") Long roleId){
    authorityService.addRoleToUser(userId,roleId);
    return "redirect:/person/"+userId;
    };

    @GetMapping("/{userId}/{roleName}")
    public String removeRoleFromUser(@PathVariable Long userId, @PathVariable("roleName") Rights roleName){
        authorityService.removeRoleFromUser(userId,roleName.name());
        return "redirect:/person/"+userId;
    };

}
