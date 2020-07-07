package pl.devzyra.todospring.model.projection;

import lombok.Getter;
import lombok.Setter;
import pl.devzyra.todospring.model.Task;
import pl.devzyra.todospring.model.TaskGroup;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class GroupReadModel {

    private String description;
    private LocalDateTime deadline;

    private Set<GroupTaskReadModel> tasks;

    public GroupReadModel(TaskGroup source) {
        description = source.getDescription();
        source.getTasks().stream()
                .map(Task::getDeadline)
                .max(LocalDateTime::compareTo)
                .ifPresent(date -> deadline = date);
        tasks = source.getTasks().stream().map(GroupTaskReadModel::new).collect(Collectors.toSet());
    }


}
