package wsb2023.pogorzelski.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import wsb2023.pogorzelski.models.Person;
import wsb2023.pogorzelski.models.Priority;
import wsb2023.pogorzelski.models.Status;
import wsb2023.pogorzelski.models.Type;

@Service
@AllArgsConstructor
public class UtilService {

    PersonService personService;



    public Status[] getAllStatus(){
        return Status.values();

    }

    public Type[] getAllTypes(){
        return  Type.values();
    }


    public Priority[] getAllPriorities() {
        return Priority.values();
    }
}
