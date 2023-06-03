package wsb2023.pogorzelski.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;

@Entity
@Data
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotEmpty()
    @NotNull()
    @Size(min=3, max=100)
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
}
