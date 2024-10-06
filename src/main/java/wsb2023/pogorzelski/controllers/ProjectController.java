package wsb2023.pogorzelski.controllers;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import wsb2023.pogorzelski.filters.IssueFilter;
import wsb2023.pogorzelski.filters.ProjectFilter;
import wsb2023.pogorzelski.models.*;
import wsb2023.pogorzelski.services.IssueService;
import wsb2023.pogorzelski.services.UtilService;
import wsb2023.pogorzelski.services.PersonService;
import wsb2023.pogorzelski.services.ProjectService;

import org.springframework.data.domain.Pageable;
import wsb2023.pogorzelski.utils.InfoUtil;

import java.io.IOException;
import java.util.List;

@Controller
@AllArgsConstructor
@EnableWebSecurity
@RequestMapping("/project")
public class ProjectController {

    ProjectService projectService;
    PersonService personService;

    UtilService utilService;
    IssueService issueService;

    @PostMapping()
    @Secured("ROLE_MANAGE_PROJECT")
    public ModelAndView addProject(@ModelAttribute @Valid Project project, BindingResult bindingResult) {
        ModelAndView model = new ModelAndView("redirect:/project/all");
        if (bindingResult.hasErrors()) {
            model.setViewName("projects/add");
            return model;
        }
        projectService.addProject(project);
        return model;
    }

    @GetMapping()
    @Secured("ROLE_MANAGE_PROJECT")
    public ModelAndView addProject() {
        ModelAndView model = new ModelAndView("projects/add");
        Project project = new Project();
        model.addObject("project", project);
        return model;
    }

    @GetMapping("/{projectId}")
    public ModelAndView showProject(@ModelAttribute IssueFilter issueFilter, Pageable pageable, @PathVariable Long projectId) {
        Project project = projectService.findProjectById(projectId);
        Page<Issue> issues = issueService.findAll(issueFilter.buildSpecification(), pageable);
        List<Issue> issueList = issues.stream().filter(issue -> issue.getProject().equals(project)).toList();
        List<Person> people = personService.findAll();
        Status[] statuses = utilService.getAllStatus();
        Priority[] allPriorities = utilService.getAllPriorities();
        ModelAndView model = new ModelAndView("projects/details");
        model.addObject("project", project);
        model.addObject("issueList", issueList);
        model.addObject("people", people);
        model.addObject("statues", statuses);
        model.addObject("priorities", allPriorities);
        model.addObject("filter", issueFilter);
        return model;
    }

    @GetMapping("/{projectId}/edit")
    @Secured("ROLE_MANAGE_PROJECT")
    public ModelAndView editProject(@PathVariable Long projectId) {
        ModelAndView model = new ModelAndView("projects/edit");
        Project project = projectService.findProjectById(projectId);
        model.addObject("project", project);
        return model;
    }

    @PostMapping("/{projectId}/edit")
    @Secured("ROLE_MANAGE_PROJECT")
    public String saveEditedProject(@ModelAttribute ProjectEditObject projectEditObject, @PathVariable Long projectId) {
        projectService.editProject(projectEditObject, projectId);
        return "redirect:/project/{projectId}";
    }

    @GetMapping("/delete/{projectId}")
    @Secured("ROLE_MANAGE_PROJECT")
    public ModelAndView deleteProject(@PathVariable Long projectId) {
        projectService.deleteProject(projectId);
        ModelAndView model = new ModelAndView("utils/info");
        InfoUtil infoUtil = new InfoUtil("Project with id " + projectId + " deleted");
        model.addObject("info", infoUtil);
        return model;
    }


    @GetMapping("/all")
    public ModelAndView allProjects(@ModelAttribute ProjectFilter projectFilter, Pageable pageable) {
        ModelAndView model = new ModelAndView("/projects/all");
        Page<Project> projects = projectService.findAll(projectFilter.buildSpecification(), pageable);
        model.addObject("projects", projects);
        List<Person> people = personService.findAll();
        model.addObject("people", people);
        model.addObject("filter", projectFilter);
        return model;


    }

    @ExceptionHandler(AccessDeniedException.class)
    public void handleError(HttpServletResponse response) throws IOException {
        response.sendRedirect("/forbidden");
    }
}
