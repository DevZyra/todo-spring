package pl.devzyra.todospring.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

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

    @NotBlank(message = "Please enter task description")
 //   @Column(name = "desc")  <- TO REMBER : can cause jpa mapping error with flyway schema creation
    private String description;
    private Boolean done;
    private LocalDateTime deadline;

    @Embedded
    private Audit audit = new Audit();

    @ManyToOne
    @JoinColumn(name = "task_group_id")
    private TaskGroup group;


    // priv getter
     private Audit getAudit() { return audit; }

    // priv setter
    private void setId(Long id) {
        this.id = id;
    }

    public void updateFrom(final Task source){
        description = source.description;
        done = source.done;
        deadline = source.deadline;
        group = source.group;
    }


}
