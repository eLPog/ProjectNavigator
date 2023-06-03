package wsb2023.pogorzelski.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import wsb2023.pogorzelski.models.Rights;
import wsb2023.pogorzelski.services.PersonService;
@AllArgsConstructor
@Service
public class Bootstrap implements InitializingBean {
   final private PersonService personService;

    @Override
    public void afterPropertiesSet() throws Exception {
            personService.saveAdmin();
    }

    public void saveMissingAuthorities(){
        for (Rights authority : Rights.values()){
            //
        }
    }
}
