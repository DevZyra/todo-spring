package pl.devzyra.todospring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;


@AllArgsConstructor
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


 //   @Column(name = "desc")  <- TO REMBER : can cause jpa mapping error with flyway schema creation
    @NotBlank(message = "Please enter task description")
    private String description;

    private Boolean done;
    private LocalDateTime deadline;

    @Embedded
    private Audit audit = new Audit();

    @ManyToOne
    @JoinColumn(name = "task_group_id")
    private TaskGroup group;

    public Task(String description,LocalDateTime deadline, TaskGroup group){
    this.description = description;
    this.deadline = deadline;
    if(group != null){
        this.group = group;
    }
    }


    @Builder
    public Task(String description,LocalDateTime deadline) {
        this(description,deadline,null);
    }

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
