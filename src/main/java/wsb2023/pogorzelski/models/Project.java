package wsb2023.pogorzelski.models;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Boolean enabled = true;

    @Column(nullable = false)
    private LocalDate dateCreated = LocalDate.now();

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne()
    @JoinColumn(name = "creator_id", nullable = false)
    private Person creator;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Issue> issueList;
}
