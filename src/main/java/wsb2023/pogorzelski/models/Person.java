package wsb2023.pogorzelski.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String realName;

    @Column(nullable = false)
    private String email;

    @ManyToMany()
    @JoinTable(name="person_authorities", joinColumns = @JoinColumn(name="person_id"), inverseJoinColumns =
    @JoinColumn(name="authority_id"))
    private Set<Authority> authorities;

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
    private List<Project> projects;

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
    private List<Issue> issueList;

    @OneToMany(mappedBy = "assignee", cascade = CascadeType.ALL)
    private List<Issue> issues;


}
