package wsb2023.pogorzelski.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import wsb2023.pogorzelski.models.Project;
import wsb2023.pogorzelski.services.AuthService;
import wsb2023.pogorzelski.services.ProjectService;

@Controller
@AllArgsConstructor
@RequestMapping("/project")
public class ProjectController {

    ProjectService projectService;


    @PostMapping()
    public String addProject(@ModelAttribute Project project) {
        projectService.addProject(project);
        return "redirect:/contact";
    }

    @GetMapping()
    public String addProject() {
       return "projects/add";
    }
}
