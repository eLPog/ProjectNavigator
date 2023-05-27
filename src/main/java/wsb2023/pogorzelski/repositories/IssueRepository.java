package wsb2023.pogorzelski.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wsb2023.pogorzelski.models.Issue;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {
}
