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
        Authority authority = authorityRepository.findById(authorityId).orElseThrow();
        Set<Authority> authorities = person.getAuthorities();
        if(authorities.contains(authority)){
            return;
        }
        authorityRepository.setAuthorityToUser(userId,authorityId);
    }

    public void removeRoleFromUser(Long userId, String authorityName){
        Authority auth = authorityRepository.findByAuthority(authorityName).orElseThrow();
            authorityRepository.removeAuthorityFromUser(userId,auth.getId());
    }

}
