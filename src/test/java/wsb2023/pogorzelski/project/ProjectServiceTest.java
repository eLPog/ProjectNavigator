package wsb2023.pogorzelski.project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.core.parameters.P;
import wsb2023.pogorzelski.controllers.PersonController;
import wsb2023.pogorzelski.models.Person;
import wsb2023.pogorzelski.models.Project;
import wsb2023.pogorzelski.repositories.PersonRepository;
import wsb2023.pogorzelski.services.PersonService;
import wsb2023.pogorzelski.services.ProjectService;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {

    @InjectMocks
    ProjectService projectService;
    @Mock
    PersonService personService;


    @Test
    void createShortNameCorrectly(){

//       assertEquals("BWP",projectService.createProjectShortName("Bardzo Wielki Projekt")

        assertAll("short name of project",
                ()->assertEquals("BWP",projectService.createProjectShortName("Bardzo Wielki Projekt")),
                ()->assertEquals("BWP",projectService.createProjectShortName("Bardzo Wyjebany Projekt"))
        );

    }
    @Test
    void createProjectDescriptionWithCreatorName(){

        when(personService.getProjectCreatorData(any(Project.class))).thenReturn("tester");

        Project project = new Project();
        project.setName("NazwaProjektu");

        String desc = projectService.createProjectDescription(project);
        assertAll("create project description with project creator name",
                ()->assertEquals("NazwaProjektu created by tester",
                        desc
                ));
    };




}
