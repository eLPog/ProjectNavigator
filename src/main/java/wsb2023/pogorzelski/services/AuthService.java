package wsb2023.pogorzelski.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import wsb2023.pogorzelski.models.Person;
import wsb2023.pogorzelski.models.Project;

@Service
@AllArgsConstructor
public class AuthService {

    public String checkLoggedUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        String username;
            if(principal instanceof UserDetails){
                 username = ((UserDetails)principal).getUsername();
            }else{
                 username = principal.toString();
            }
            return username;
    };



}
