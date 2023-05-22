package wsb2023.pogorzelski.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wsb2023.pogorzelski.models.Person;
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





}
