package wsb2023.pogorzelski.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import wsb2023.pogorzelski.models.Issue;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {

    @Query(value="SELECT * FROM issue WHERE project_id = :projectId",
    nativeQuery = true)
    List<Issue> findAllByProjectId(@Param("projectId") Long projectId);
}
