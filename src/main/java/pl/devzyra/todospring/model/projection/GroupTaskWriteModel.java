package pl.devzyra.todospring.model.projection;

import lombok.Getter;
import lombok.Setter;
import pl.devzyra.todospring.model.Task;

import java.time.LocalDateTime;

@Getter
@Setter
public class GroupTaskWriteModel {

    private String description;
    private LocalDateTime deadline;


    public Task toTask(){
        return new Task(description,deadline);
    }

}
