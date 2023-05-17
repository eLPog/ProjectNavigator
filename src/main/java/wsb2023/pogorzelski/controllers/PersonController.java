package wsb2023.pogorzelski.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import wsb2023.pogorzelski.models.Person;
import wsb2023.pogorzelski.services.PersonService;

import java.util.List;

@Controller
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

   final private PersonService personService;


    @GetMapping
    ModelAndView getAllPersons(){
        ModelAndView model = new ModelAndView("person/allPersons");
        List<Person> allUsers = personService.findAll();
        model.addObject("allUsers",allUsers);
        return model;
    }


}
