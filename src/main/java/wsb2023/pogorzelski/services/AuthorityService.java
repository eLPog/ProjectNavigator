package wsb2023.pogorzelski.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import wsb2023.pogorzelski.models.Authority;
import wsb2023.pogorzelski.models.Person;
import wsb2023.pogorzelski.repositories.AuthorityRepository;
import wsb2023.pogorzelski.repositories.PersonRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthorityService {

    final private AuthorityRepository authorityRepository;
    final private PersonRepository personRepository;


    public List<Authority> findAll(){
        return authorityRepository.findAll();
    };

    public void addRoleToUser(Long userId, Long authorityId){
        Person person = personRepository.findById(userId).orElseThrow();
        List<Authority> personAuthorities = person.getAuthorities();
        Authority authority = authorityRepository.findById(authorityId).orElseThrow();
       if(!personAuthorities.contains(authority)){
           personAuthorities.add(authority);
       }
        personRepository.save(person);
    }

    public void removeRoleFromUser(Long userId, String authorityName){
        Authority authority = authorityRepository.findByAuthority(authorityName).orElseThrow();
        Person person = personRepository.findById(userId).orElseThrow();
        List<Authority> authorities = person.getAuthorities();
        if(authorities.contains(authority)){
            authorities.remove(authority);
            personRepository.save(person);
        }

    }

    public void addAllRolesToAdmin(){
        Person admin = personRepository.findByUsername("Admin").orElseThrow();
        List<Authority> authorities = admin.getAuthorities();
        List<Authority> allAuthorities = authorityRepository.findAll();
        for(Authority auth:allAuthorities){
           if(authorities.contains(auth)){
               continue;
           }else{
               authorities.add(auth);
               personRepository.save(admin);
           }
        }
    }

}
