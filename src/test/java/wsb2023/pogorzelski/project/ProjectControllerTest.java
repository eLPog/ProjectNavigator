package wsb2023.pogorzelski.project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import wsb2023.pogorzelski.controllers.ProjectController;

import static org.assertj.core.api.Assertions.assertThat;

@WebMvcTest(ProjectController.class)
@ExtendWith(MockitoExtension.class)
public class ProjectControllerTest {

    @Autowired
    ProjectController projectController;


    @Autowired
    private MockMvc mockMvc;
    @WithMockUser(username = "testowy")
    @Test
    public void controllerExist() throws Exception{
        assertThat(projectController).isNotNull();
    }

    @Test
    public void listOfProjectsReturnSuccess() throws Exception {

    }
}
