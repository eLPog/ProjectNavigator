package wsb2023.pogorzelski.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import wsb2023.pogorzelski.models.*;
import wsb2023.pogorzelski.services.IssueService;
import wsb2023.pogorzelski.services.PersonService;
import wsb2023.pogorzelski.services.ProjectService;
import wsb2023.pogorzelski.services.UtilService;

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
    public ModelAndView addIssueToProject(@PathVariable  Long projectId){
        ModelAndView model = new ModelAndView("issues/add");
        Person person = personService.getLoggedUser();
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
    @GetMapping("/assign/{issueId}")
    public String assign(@PathVariable Long issueId){
        issueService.assignUserToIssue(issueId);
        return "redirect:/project/all";
    }

    @GetMapping("/delete/{issueId}")
    public String delete(@PathVariable Long issueId){
        issueService.deleteIssue(issueId);
        return "redirect:/project/all";
    }

    @GetMapping("/edit/{issueId}")
    public ModelAndView edit(@PathVariable Long issueId){
        ModelAndView model = new ModelAndView("issues/edit");
        Issue issue = issueService.findById(issueId);
        model.addObject("issue",issue);
        return model;
    }

    @PostMapping("/edit/{issueId}")
    public String editIssue(@PathVariable Long issueId, @ModelAttribute IssueEditObject issueEditObject){
        issueService.editIssue(issueEditObject, issueId);
        return "redirect:/issue/" + issueId;
    }

}
