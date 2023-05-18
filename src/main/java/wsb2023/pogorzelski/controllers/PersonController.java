package wsb2023.pogorzelski.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import wsb2023.pogorzelski.models.Person;
import wsb2023.pogorzelski.services.PersonService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Controller
@RequestMapping("/person")
@RequiredArgsConstructor
@EnableWebSecurity
public class PersonController {

   final private PersonService personService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @GetMapping("/all")
    @Secured("ROLE_MANAGE_USERS")
    ModelAndView getAllPersons(){
        ModelAndView model = new ModelAndView("person/allPersons");
        List<Person> allUsers = personService.findAll();
        model.addObject("allUsers",allUsers);
        return model;
    }

    @GetMapping("/create")
    @Secured("ROLE_MANAGE_USERS")
    ModelAndView createUser(){
        ModelAndView model = new ModelAndView("person/create");
        model.addObject("person", new Person());
        return model;
    }

    @PostMapping("/create")
    @Secured("ROLE_MANAGE_USERS")
    ModelAndView saveNewUser(@ModelAttribute Person person){
        ModelAndView model = new ModelAndView();
        person.setPassword(bCryptPasswordEncoder.encode(person.getPassword()));
        personService.addNewUser(person);
        model.setViewName("redirect:/person/all");
        return model;
    }

    @GetMapping()
    public String testShow(){
        return "test";
    }


    


}
