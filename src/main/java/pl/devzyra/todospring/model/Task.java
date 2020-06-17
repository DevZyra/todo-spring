package pl.devzyra.todospring.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Data
@Entity
@Table(name = "tasks")
public class Task {

    /* It's considered convention to use wrapper classes with Hibernate ORM
    Can insert nulls into DB if nullable, if not might throw exception */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Please enter description")
    @Column(name = "desc")
    private String description;
    private Boolean done;
}
