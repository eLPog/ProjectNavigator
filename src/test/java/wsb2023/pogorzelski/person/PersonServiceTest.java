package wsb2023.pogorzelski.person;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;
import wsb2023.pogorzelski.models.Person;
import wsb2023.pogorzelski.models.Project;
import wsb2023.pogorzelski.repositories.AuthorityRepository;
import wsb2023.pogorzelski.repositories.PersonRepository;
import wsb2023.pogorzelski.services.PersonService;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @InjectMocks
    PersonService personService;
    @Mock
    PersonRepository personRepository;
    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Mock
    AuthorityRepository authorityRepository;


    @Test
    void isAdminCorrectlySavedOnAppStart(){

        ReflectionTestUtils.setField(personService,"username","Admin");
        ReflectionTestUtils.setField(personService,"password","haslo");

        personService.saveAdmin();
        verify(personRepository, times(1)).findByUsername(anyString());
        verify(bCryptPasswordEncoder,times(1)).encode(anyString());
    }

    @Test
    void getCreatorNameFromProject(){
        Project project = new Project();
        Person person = new Person();
        person.setRealName("Waldek");
        project.setCreator(person);

        String creatorName = personService.getProjectCreatorData(project);
        assertAll("read Creator name from Project",
                ()->assertEquals("Waldek",
                        creatorName
                ));
    };


    }

