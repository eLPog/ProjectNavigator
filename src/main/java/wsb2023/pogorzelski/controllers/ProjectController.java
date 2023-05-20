package wsb2023.pogorzelski.controllers;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import wsb2023.pogorzelski.filters.ProjectFilter;
import wsb2023.pogorzelski.models.Person;
import wsb2023.pogorzelski.models.Project;
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


    @PostMapping()
    public String addProject(@ModelAttribute Project project) {
        projectService.addProject(project);
        return "redirect:/contact";
    }

    @GetMapping()
    public String addProject() {
       return "projects/add";
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

//@TODO show all projects with filters
}
