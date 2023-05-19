package wsb2023.pogorzelski.services;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import wsb2023.pogorzelski.models.Person;
import wsb2023.pogorzelski.repositories.PersonRepository;

import java.util.List;
import java.util.Optional;

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

    public Optional<Person> findUserById(Long id){
        return this.personRepository.findById(id);
    };

    public Person findUserByName(String username){
        return this.personRepository.findByUsername(username).orElseThrow();
    }


}
