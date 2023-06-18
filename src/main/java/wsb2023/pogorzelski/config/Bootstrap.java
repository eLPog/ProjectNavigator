package wsb2023.pogorzelski.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;

import org.springframework.stereotype.Service;

import wsb2023.pogorzelski.services.AuthorityService;
import wsb2023.pogorzelski.services.PersonService;


@Service
@RequiredArgsConstructor
public class Bootstrap implements InitializingBean {
    final private PersonService personService;



    @Override
    public void afterPropertiesSet() throws Exception {
        personService.saveAdmin();

    }


}


