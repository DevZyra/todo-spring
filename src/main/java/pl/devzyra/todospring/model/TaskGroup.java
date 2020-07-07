package pl.devzyra.todospring.model;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "task_groups")
public class TaskGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Please enter task group description")
    //   @Column(name = "desc")  <- TO REMBER : can cause jpa mapping error with flyway schema creation
    private String description;
    private Boolean done;

    public TaskGroup() {
    }

    // Hibernate has its own implem. of the list that doesnt persist insertion order:? so set might be better opt.
    // With lazy fetch type , Hibernate imports tasks when getter is called at
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy = "group")
    private Set<Task> tasks;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

}
