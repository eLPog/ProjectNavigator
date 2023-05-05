package wsb2023.pogorzelski.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import wsb2023.pogorzelski.models.Person;
import wsb2023.pogorzelski.models.Project;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
