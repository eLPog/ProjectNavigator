package wsb2023.pogorzelski.project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import wsb2023.pogorzelski.controllers.ProjectController;
import wsb2023.pogorzelski.services.IssueService;
import wsb2023.pogorzelski.services.PersonService;
import wsb2023.pogorzelski.services.ProjectService;
import wsb2023.pogorzelski.services.UtilService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(ProjectController.class)
@ExtendWith(MockitoExtension.class)
public class ProjectControllerTest {

    @Autowired
    ProjectController projectController;

    @MockBean
    ProjectService projectService;
    @MockBean
    PersonService personService;
    @MockBean
    UtilService utilService;
    @MockBean
    IssueService issueService;


    @Autowired
    private MockMvc mockMvc;

    @WithMockUser(username = "testowy")
    @Test
    public void controllerExist() throws Exception {
        assertThat(projectController).isNotNull();
    }

    @WithMockUser(username = "testowy", roles = {"MANAGE_USERS"})
    @Test
    public void listOfProjectsReturnSuccess() throws Exception {
        mockMvc.perform(get("/project/all"))
                .andExpect(status().isOk())
                .andExpect(view().name("/projects/all"));
    }

    @Test
    public void listOfProjectsWithoutAuthorisationReturnStatusUnauthorized() throws Exception {
        mockMvc.perform(get("/project/all"))
                .andExpect(status().isUnauthorized());
    }

    @WithMockUser(username = "testowy", roles = {"MANAGE_USERS"})
    @Test
    public void redirectAfterNewProjectCreate() throws Exception{
        mockMvc.perform(post("/project")
                        .with(csrf())
                        .param("name","newProject")
                        .param("description","Test Project"))
                .andExpect(view().name("redirect:/project/all"))
                .andExpect(status().is3xxRedirection());

    }
}
