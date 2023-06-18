package wsb2023.pogorzelski.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotNull()
    @Size(min=3, max=100,message = "Username must have min. 3 and max. 100 characters")
    private String username;

    @Column(nullable = false)
    @NotNull()
    @Size(min=5, max=100,message = "Password must have min. 5 characters")
    private String password;

    @Column(nullable = false)
    @NotNull()
    @Size(min=3, max=100,message = "Real name must have min. 3 and max. 100 characters")
    private String realName;

    @Column(nullable = false)
    @NotEmpty(message = "Email can not be empty")
    @Email(message = "Email format not valid")
    private String email;

    @ManyToMany()
    @JoinTable(name="person_authorities", joinColumns = @JoinColumn(name="person_id"), inverseJoinColumns =
    @JoinColumn(name="authority_id"))
    private List<Authority> authorities;

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
    private List<Project> projects;

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
    private List<Issue> issueList;

    @OneToMany(mappedBy = "assignee", cascade = CascadeType.ALL)
    private List<Issue> issues;


}
