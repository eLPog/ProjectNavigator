package wsb2023.pogorzelski.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wsb2023.pogorzelski.services.PersonService;
@AllArgsConstructor
@Service
public class Bootstrap implements InitializingBean {
    private PersonService personService;

    @Override
    public void afterPropertiesSet() throws Exception {
            personService.saveAdmin();
    }
}
