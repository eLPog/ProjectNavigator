package wsb2023.pogorzelski.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import wsb2023.pogorzelski.filters.IssueFilter;
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


    @GetMapping("/all")
    public ModelAndView getAllIssues(@ModelAttribute IssueFilter issueFilter, Pageable pageable) {
        ModelAndView model = new ModelAndView("issues/all");
        Page<Issue> issues = issueService.findAll(issueFilter.buildSpecification(), pageable);
        List<Person> people = personService.findAll();
        Status[] statuses = utilService.getAllStatus();
        List <Project> projects = projectService.getAllWithoutFilter();
        model.addObject("issueList", issues);
        model.addObject("people", people);
        model.addObject("filter", issueFilter);
        model.addObject("statues", statuses);
        model.addObject("projects", projects);
        return model;
    }


    //@TODO Walidacja dziala, ale nie pokazuje informacji o błędzie
    @PostMapping("/add")
    public ModelAndView addIssueToProject(@ModelAttribute @Valid IssueAddObject issueAddObject,
                                          BindingResult bindingResult) {
        ModelAndView model = new ModelAndView("redirect:/project/"+issueAddObject.getProjectId());
        Person person = personService.getLoggedUser();
        Status[] statuses = utilService.getAllStatus();
        Type[] types = utilService.getAllTypes();
        Priority[] priorities = utilService.getAllPriorities();
        IssueAddObject issue = new IssueAddObject();
        model.addObject("issue", issue);
        model.addObject("projectId", issueAddObject.getProjectId());
        model.addObject("person", person);
        model.addObject("statues", statuses);
        model.addObject("types", types);
        model.addObject("priorities", priorities);
        if (bindingResult.hasErrors()) {
            model.setViewName("issues/add");
            return model;
        }
        issueService.addIssueToProject(issueAddObject);
        return model;
    }

    @GetMapping("/add/{projectId}")
    public ModelAndView addIssueToProject(@PathVariable Long projectId) {
        ModelAndView model = new ModelAndView("issues/add");
        Person person = personService.getLoggedUser();
        Status[] statuses = utilService.getAllStatus();
        Type[] types = utilService.getAllTypes();
        Priority[] priorities = utilService.getAllPriorities();
        IssueAddObject issue = new IssueAddObject();
        model.addObject("issue", issue);
        model.addObject("projectId", projectId);
        model.addObject("person", person);
        model.addObject("statues", statuses);
        model.addObject("types", types);
        model.addObject("priorities", priorities);
        return model;
    }

    @GetMapping("/{issueId}")
    public ModelAndView issueDetails(@PathVariable Long issueId) {
        Issue issue = issueService.findById(issueId);
        ModelAndView model = new ModelAndView("issues/details");
        model.addObject("issue", issue);
        return model;
    }


    @GetMapping("/assign/{issueId}")
    public String assign(@PathVariable Long issueId) {
        issueService.assignUserToIssue(issueId);
        return "redirect:/issue/" + issueId;
    }

    @GetMapping("/delete/{issueId}")
    public String delete(@PathVariable Long issueId) {
        issueService.deleteIssue(issueId);
        return "redirect:/project/all";
    }

    @GetMapping("/edit/{issueId}")
    public ModelAndView edit(@PathVariable Long issueId) {
        ModelAndView model = new ModelAndView("issues/edit");
        Issue issue = issueService.findById(issueId);
        model.addObject("issue", issue);
        return model;
    }

    @PostMapping("/edit/{issueId}")
    public String editIssue(@PathVariable Long issueId, @ModelAttribute IssueEditObject issueEditObject) {
        issueService.editIssue(issueEditObject, issueId);
        return "redirect:/issue/" + issueId;
    }
    @GetMapping("update/{issueId}/{status}")
    public String issueDetails(@PathVariable Long issueId, @PathVariable Status status) {
        issueService.updateStatus(status,issueId);
        return "redirect:/issue/" + issueId;
    }


}
