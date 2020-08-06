package pl.devzyra.todospring.model.event;

import pl.devzyra.todospring.model.Task;

import java.time.Clock;

public class TaskDone extends TaskEvent {
    public TaskDone(Task source) {
        super(source.getId(), Clock.systemDefaultZone());
    }
}
