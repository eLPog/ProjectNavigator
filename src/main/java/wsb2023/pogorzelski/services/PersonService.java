package wsb2023.pogorzelski.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import wsb2023.pogorzelski.models.Person;
import wsb2023.pogorzelski.models.UserEditObject;
import wsb2023.pogorzelski.repositories.PersonRepository;


import java.util.List;


@Service
@RequiredArgsConstructor
public class PersonService {

    final private PersonRepository personRepository;

    public List<Person> findAll(){
        return personRepository.findAll();
    }

    public void addNewUser(Person person){
        this.personRepository.save(person);
    }

    public Person findUserById(Long id){
        return this.personRepository.findById(id).orElseThrow();
    };

    public Person findUserByName(String username){
        return this.personRepository.findByUsername(username).orElseThrow();
    }

    public List<String> getUserAuthorities(Long userId){
        return personRepository.getAuthoritiesAndUsernames(userId);
    }

    public void editUserData(UserEditObject userEditObject){
        Person userOriginal = this.getLoggedUser();
        userOriginal.setUsername(userEditObject.getUsername());
        userOriginal.setRealName(userEditObject.getRealName());
        userOriginal.setEmail(userEditObject.getEmail());
        personRepository.save(userOriginal);
    }

    private String checkLoggedUserName(){
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

    public Person getLoggedUser(){
        String userName = this.checkLoggedUserName();
        return this.findUserByName(userName);
    }



}
