package wsb2023.pogorzelski.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Getter
@Setter
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotNull()
    @Size(min = 3, max = 100, message = "Project name must have min. 3 and max. 100 characters")
    private String name;

    @Column(nullable = false)
    private Boolean enabled = true;

    @Column(nullable = false)
    private String dateCreated = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

    @Column(columnDefinition = "TEXT")
    @NotNull()
    @Size(min = 5, message = "Description must have min. 5 characters")
    private String description;

    @ManyToOne()
    @JoinColumn(name = "creator_id", nullable = false)
    private Person creator;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Issue> issueList;

    public int getIssuesCount() {
        return issueList != null ? issueList.size() : 0;
    }

    public long getToDoesIssuesCount(){
        return issueList.stream().filter(issue -> issue.getStatus().equals(Status.TODO)).count();
    }
    public long getDoneIssuesCount(){
        return issueList.stream().filter(issue -> issue.getStatus().equals(Status.DONE)).count();
    }
    public long getInProgressIssuesCount(){
        return issueList.stream().filter(issue -> issue.getStatus().equals(Status.IN_PROGRESS)).count();
    }
    public long getBlockedIssuesCount(){
        return issueList.stream().filter(issue -> issue.getStatus().equals(Status.BLOCKED)).count();
    }


}
