package wsb2023.pogorzelski.repositories;

import jakarta.transaction.Transactional;
import org.hibernate.annotations.SQLInsert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import wsb2023.pogorzelski.models.Authority;
import wsb2023.pogorzelski.models.Project;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    Optional<Authority> findByAuthority(String authorityName);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "INSERT INTO person_authorities VALUES (:userId, :roleId)", nativeQuery = true)
    void setAuthorityToUser(@Param("userId") Long userId, @Param("roleId") Long authId);

    @Transactional
    @Modifying
    @Query(value="DELETE FROM person_authorities WHERE person_id=:userId and  authority_id = :roleId",
            nativeQuery = true)
    void removeAuthorityFromUser(@Param("userId") Long userId, @Param("roleId") Long authId);

}
