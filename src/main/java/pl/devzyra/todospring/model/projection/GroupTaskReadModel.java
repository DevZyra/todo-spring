package pl.devzyra.todospring.model.projection;

import lombok.Getter;
import lombok.Setter;
import pl.devzyra.todospring.model.Task;

@Getter
@Setter
public class GroupTaskReadModel {

    private String description;
    private Boolean done;

    public GroupTaskReadModel(Task source) {
    description= source.getDescription();
    done = source.getDone();
    }
}
