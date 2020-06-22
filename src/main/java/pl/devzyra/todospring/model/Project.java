package pl.devzyra.todospring.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@NoArgsConstructor
@Data
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Please enter project description")
    private String description;

    @OneToMany(mappedBy = "project")
    private Set<TaskGroup> groups;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "project")
    private Set<ProjectStep> steps;






    private void setId(Long id) {
        this.id = id;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    private Set<TaskGroup> getGroups() {
        return groups;
    }

    private void setGroups(Set<TaskGroup> groups) {
        this.groups = groups;
    }
}
