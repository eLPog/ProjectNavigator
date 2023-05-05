package wsb2023.pogorzelski.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, unique = true)
    String authority;
}
