package pl.devzyra.todospring.model.event;

import pl.devzyra.todospring.model.Task;

import java.time.Clock;

public class TaskUndone extends TaskEvent {
    public TaskUndone(Task source) {
        super(source.getId(), Clock.systemDefaultZone());
    }
}
