package wsb2023.pogorzelski.controllers;

import groovyjarjarasm.asm.commons.ModuleHashesAttribute;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import wsb2023.pogorzelski.models.*;
import wsb2023.pogorzelski.services.IssueService;
import wsb2023.pogorzelski.services.PersonService;
import wsb2023.pogorzelski.services.ProjectService;
import wsb2023.pogorzelski.services.UtilService;

import java.util.List;

@Controller
@RequestMapping("/issue")
@AllArgsConstructor

public class IssueController {

    ProjectService projectService;
    UtilService utilService;
    PersonService personService;
    IssueService issueService;


    @PostMapping("/add")
   public String addIssueToProject(@ModelAttribute IssueAddObject issueAddObject){
       issueService.addIssueToProject(issueAddObject);

        return "redirect:/project/all";
    }

    @GetMapping("/add/{projectId}")
    public ModelAndView addIssueToProject(@PathVariable Long projectId){
        ModelAndView model = new ModelAndView("issues/add");
        String userName = utilService.checkLoggedUserName();
        Person person = personService.findUserByName(userName);
        Status[] statuses = utilService.getAllStatus();
        Type[] types = utilService.getAllTypes();
        Priority[] priorities = utilService.getAllPriorities();
        model.addObject("projectId", projectId);
        model.addObject("person", person);
        model.addObject("statues", statuses);
        model.addObject("types", types);
        model.addObject("priorities", priorities);
        return model;
    }

    @GetMapping("/{issueId}")
    public ModelAndView issueDetails(@PathVariable Long issueId){
        Issue issue = issueService.findById(issueId);
        ModelAndView model = new ModelAndView("issues/details");
        model.addObject("issue",issue);
        return model;
    }

}
