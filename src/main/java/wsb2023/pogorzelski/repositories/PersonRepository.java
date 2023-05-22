package wsb2023.pogorzelski.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import wsb2023.pogorzelski.models.Person;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

Optional<Person> findByUsername(String username);



    @Query(value="SELECT authority.authority FROM authority  JOIN person_authorities ON authority.id" +
            " = person_authorities.authority_id JOIN person ON person_authorities.person_id = person.id where person" +
            ".id=:userId", nativeQuery = true)
    List<String> getAuthoritiesAndUsernames(@Param("userId") Long personId);
}
