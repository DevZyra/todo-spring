package pl.devzyra.todospring.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Data
@Entity
@Table(name = "project_steps")
public class ProjectStep {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Please enter step description")
    private String description;
    private int daysToDeadline;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;


}
