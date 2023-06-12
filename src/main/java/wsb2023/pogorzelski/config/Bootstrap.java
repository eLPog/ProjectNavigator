package wsb2023.pogorzelski.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Value;
=======
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> tests
import org.springframework.stereotype.Service;
import wsb2023.pogorzelski.models.Authority;
import wsb2023.pogorzelski.models.Person;
import wsb2023.pogorzelski.models.Rights;
import wsb2023.pogorzelski.services.AuthorityService;
import wsb2023.pogorzelski.services.PersonService;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class Bootstrap implements InitializingBean {
<<<<<<< HEAD
   final private PersonService personService;
   final private AuthorityService authorityService;
=======
    private PersonService personService;
>>>>>>> tests

    @Override
    public void afterPropertiesSet() throws Exception {
            personService.saveAdmin();
    }

    private void saveMissingAuthorities(){

    }
}
