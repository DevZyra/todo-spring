package pl.devzyra.todospring.model.event;

import pl.devzyra.todospring.model.Task;

import java.time.Clock;
import java.time.Instant;

public abstract class  TaskEvent {

    public static TaskEvent changed(Task source){
      return source.isDone() ? new TaskDone(source) : new TaskUndone(source);
    }


    private Long taskId;
    private Instant occurrence;


    TaskEvent(Long taskId, Clock clock) {
        this.taskId = taskId;
        this.occurrence = Instant.now(clock);
    }

    public Long getTaskId() {
        return taskId;
    }

    public Instant getOccurrence() {
        return occurrence;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "TaskEvent{" +
                "taskId=" + taskId +
                ", occurrence=" + occurrence +
                '}';
    }
}
