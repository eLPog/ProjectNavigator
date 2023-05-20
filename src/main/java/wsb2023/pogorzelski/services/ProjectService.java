package wsb2023.pogorzelski.services;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wsb2023.pogorzelski.models.Person;
import wsb2023.pogorzelski.models.Project;
import wsb2023.pogorzelski.models.ProjectEditObject;
import wsb2023.pogorzelski.repositories.ProjectRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService {
    final private ProjectRepository projectRepository;
    final private AuthService authService;
    final private PersonService personService;

    public Page<Project> findAll(Specification<Project> specification, Pageable pageable) {
        return projectRepository.findAll(specification, pageable);
    }

    public void addProject(Project project) {
        String username = authService.checkLoggedUserName();
        Person user = this.personService.findUserByName(username);
        project.setCreator(user);
        projectRepository.save(project);
    }

    public void deleteProject(Long id){
        projectRepository.deleteById(id);
    }


    public Project findProjectById(Long projectId) {
        return projectRepository.findById(projectId).orElseThrow();
    }
    @Transactional
    public void editProject(ProjectEditObject projectEditObject, Long projectId){
        Project originalProject = this.findProjectById(projectId);
        originalProject.setName(projectEditObject.getName());
        originalProject.setDescription(projectEditObject.getDescription());
        originalProject.setEnabled(projectEditObject.getIsEnabled());
        projectRepository.save(originalProject);
    }
}
