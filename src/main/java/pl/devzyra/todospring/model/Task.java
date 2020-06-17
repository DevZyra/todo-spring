package pl.devzyra.todospring.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tasks")
public class Task {

    /* It's considered convention to use wrapper classes with Hibernate ORM
    Can insert nulls into DB if nullable, if not might throw exception */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "desc")
    private String description;
    private Boolean done;
}
