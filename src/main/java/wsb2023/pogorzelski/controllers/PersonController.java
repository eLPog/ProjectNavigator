package wsb2023.pogorzelski.controllers;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import wsb2023.pogorzelski.models.Authority;
import wsb2023.pogorzelski.models.Person;
import wsb2023.pogorzelski.models.UserEditObject;
import wsb2023.pogorzelski.services.AuthorityService;
import wsb2023.pogorzelski.services.PersonService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import wsb2023.pogorzelski.utils.InfoUtil;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/person")
@RequiredArgsConstructor
@EnableWebSecurity
public class PersonController {
    @Autowired
    final private PersonService personService;
    final private AuthorityService authorityService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @GetMapping("/all")
    @Secured("ROLE_USER_TAB")
    ModelAndView getAllPersons() {
        ModelAndView model = new ModelAndView("person/allPersons");
        List<Person> allUsers = personService.findAll();
        model.addObject("allUsers", allUsers);
        return model;
    }

    @GetMapping("/create")
    @Secured("ROLE_MANAGE_USERS")
    ModelAndView createUser() {
        ModelAndView model = new ModelAndView("person/create");
        Person person = new Person();
        model.addObject("person", person);
        return model;
    }

    @PostMapping("/create")
    @Secured("ROLE_MANAGE_USERS")
    ModelAndView saveNewUser(@ModelAttribute @Valid Person person, BindingResult bindingResult) {
        ModelAndView model = new ModelAndView("redirect:/person/all");
        if(bindingResult.hasErrors()){
            model.setViewName("person/create");
            return model;
        }
        person.setPassword(bCryptPasswordEncoder.encode(person.getPassword()));
        personService.addNewUser(person);
        model.setViewName("redirect:/person/all");
        return model;
    }

    @GetMapping("/{personId}")
    @Secured("ROLE_MANAGE_USERS")
    ModelAndView showUserDetails(@PathVariable Long personId) {
        ModelAndView model = new ModelAndView("person/details");
        Person person = personService.findUserById(personId);
        List<String> authorities = personService.getUserAuthorities(personId);
        List<Authority> allAvailableAuthorities = authorityService.findAll();
        model.addObject("allAuthorities", allAvailableAuthorities);
        model.addObject("person", person);
        model.addObject("authorities", authorities);
        return model;
    }


    @GetMapping("/{personId}/edit")
    @Secured("ROLE_MANAGE_USERS")
    ModelAndView editUser(@PathVariable Long personId) {
        ModelAndView model = new ModelAndView("person/edit");
        Person person = personService.findUserById(personId);
        model.addObject("person", person);
        return model;
    }
    @PostMapping("/{personId}/edit")
    @Secured("ROLE_MANAGE_USERS")
    String editUserFromAdmin(@ModelAttribute UserEditObject userEditObject, @PathVariable Long personId) {
        personService.changeUserDataFromAdmin(userEditObject,personId);
        return "redirect:/person/" + personId ;
    }
    @GetMapping("/{personId}/reset")
    @Secured("ROLE_MANAGE_USERS")
    ModelAndView resetAnotherUserPassword(@PathVariable Long personId) {
        ModelAndView model = new ModelAndView("utils/info");
        Person userById = personService.findUserById(personId);
        String username = userById.getUsername();
        personService.resetPassword(username);
        InfoUtil infoUtil = new InfoUtil("Password for user " + username + " reset");
        model.addObject("info", infoUtil);
        return model;
    }

    @GetMapping("/reset")
    ModelAndView showResetPasswordForm(){
        ModelAndView model = new ModelAndView("person/resetPass");
        return model;
    }


    @PostMapping("/reset")
    ModelAndView resetPassword(@RequestParam ("username") String username){
        ModelAndView model = new ModelAndView("open/resetPasswordConfirm");
        personService.resetPassword(username);
        return model;
    }



    @GetMapping("/{personId}/delete")
    @Secured("ROLE_MANAGE_USERS")
    String deleteUserFromAdmin(@PathVariable Long personId){
        personService.deleteUser(personId);
        return "redirect:/person/all";
    }


    @GetMapping("/myAccount")
    ModelAndView userAccount() {
        ModelAndView model = new ModelAndView("person/userAccount");
        Person person = personService.getLoggedUser();
        model.addObject("person", person);
        return model;
    }
    @PostMapping("/myAccount")
    String editUserData(@ModelAttribute UserEditObject userEditObject){
        personService.editUserData(userEditObject);
        return "redirect:/person/myAccount";
    }


    @GetMapping("/{personId}/roles")
    @Secured("ROLE_MANAGE_USERS")
    ModelAndView userRoles(@PathVariable Long personId) {
        ModelAndView model = new ModelAndView("person/roles");
        Person person = personService.findUserById(personId);
        List<String> authorities = personService.getUserAuthorities(personId);

        model.addObject("authorities", authorities);
        model.addObject("person", person);

        return model;
    }

    @ExceptionHandler(AccessDeniedException.class)
    public void handleError(HttpServletResponse response) throws IOException {
        response.sendRedirect("/forbidden");
    }


}
