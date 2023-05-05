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
}
