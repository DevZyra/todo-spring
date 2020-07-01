package pl.devzyra.todospring.model.projection;

import lombok.Getter;
import lombok.Setter;
import pl.devzyra.todospring.model.TaskGroup;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class GroupWriteModel {

    private String description;
    private Set<GroupTaskWriteModel> tasks;

    public TaskGroup toGroup(){
            var result = new TaskGroup();
            result.setDescription(description);
            result.setTasks(
                    tasks.stream()
                    .map(s->s.toTask(result))
                    .collect(Collectors.toSet()));
            return result;
    }
}
