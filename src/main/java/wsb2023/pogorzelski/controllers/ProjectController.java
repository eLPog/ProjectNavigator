package wsb2023.pogorzelski.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import wsb2023.pogorzelski.filters.ProjectFilter;
import wsb2023.pogorzelski.models.Issue;
import wsb2023.pogorzelski.models.Person;
import wsb2023.pogorzelski.models.Project;
import wsb2023.pogorzelski.models.ProjectEditObject;
import wsb2023.pogorzelski.services.IssueService;
import wsb2023.pogorzelski.services.UtilService;
import wsb2023.pogorzelski.services.PersonService;
import wsb2023.pogorzelski.services.ProjectService;

import org.springframework.data.domain.Pageable;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/project")
public class ProjectController {

    ProjectService projectService;
    PersonService personService;

    UtilService utilService;
    IssueService issueService;

    @PostMapping()
    public ModelAndView addProject(@ModelAttribute @Valid Project project, BindingResult bindingResult) {
        ModelAndView model = new ModelAndView("redirect:/project/all");
        if(bindingResult.hasErrors()){
            model.setViewName("projects/add");
            return model;
        }
        projectService.addProject(project);
        return model;
    }

    @GetMapping()
    public ModelAndView addProject() {
        ModelAndView model = new ModelAndView("projects/add");
        Project project = new Project();
        model.addObject("project",project);
       return model;
    }


    @GetMapping("/{projectId}")
    public ModelAndView showProject(@PathVariable Long projectId){
        Project project = projectService.findProjectById(projectId);
        List<Issue> issueList = issueService.allIssuesForProject(projectId);
        ModelAndView model = new ModelAndView("projects/details");
        model.addObject("project",project);
        model.addObject("issueList",issueList);
        return model;
    }
    @GetMapping("/{projectId}/edit")
    public ModelAndView editProject(@PathVariable Long projectId){
        ModelAndView model = new ModelAndView("projects/edit");
        Boolean isLoggedUserCreator = projectService.isLoggedUserCreator(projectId);
        if(isLoggedUserCreator){
            Project project = projectService.findProjectById(projectId);
            model.addObject("project",project);
        }
        model.addObject("isUserCreator",isLoggedUserCreator);
        return model;
    }

    @PostMapping("/{projectId}/edit")
    public String saveEditedProject(@ModelAttribute ProjectEditObject projectEditObject, @PathVariable Long projectId){
        projectService.editProject(projectEditObject, projectId);
        return "redirect:/project/{projectId}";
    }

    @PostMapping("/{projectId}")
    public String deleteProject(@PathVariable Long projectId){
        Boolean isLoggedUserCreator = projectService.isLoggedUserCreator(projectId);
        if(isLoggedUserCreator){
            projectService.deleteProject(projectId);
            return "redirect:/project/all";
        }
        return "redirect:/forbidden";

    }


    @GetMapping("/all")
    public ModelAndView allProjects(@ModelAttribute ProjectFilter projectFilter, Pageable pageable){
        ModelAndView model = new ModelAndView("/projects/all");
        Page<Project> projects = projectService.findAll(projectFilter.buildSpecification(),pageable);
        model.addObject("projects",projects);
        List<Person> people = personService.findAll();
        model.addObject("people",people);
        model.addObject("filter",projectFilter);
        return model;


    }

}
