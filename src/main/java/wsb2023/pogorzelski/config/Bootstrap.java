package wsb2023.pogorzelski.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import wsb2023.pogorzelski.services.AuthorityService;
import wsb2023.pogorzelski.services.PersonService;


@AllArgsConstructor
@Service
public class Bootstrap implements InitializingBean {
   final private PersonService personService;
   final private AuthorityService authorityService;

    @Override
    public void afterPropertiesSet() throws Exception {
            personService.saveAdmin();
    }

    private void saveMissingAuthorities(){

    }
}
