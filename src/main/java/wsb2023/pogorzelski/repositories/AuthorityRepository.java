package wsb2023.pogorzelski.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wsb2023.pogorzelski.models.Authority;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long>{

    Optional<Authority> findByAuthority(String authorityName);


}
