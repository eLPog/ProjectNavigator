package wsb2023.pogorzelski.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
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

    @ManyToMany
    @JoinTable(name="person_authorities", joinColumns = @JoinColumn(name="person_id"), inverseJoinColumns =
    @JoinColumn(name="authority_id"))
    private Set<Authority> authorities;

}
