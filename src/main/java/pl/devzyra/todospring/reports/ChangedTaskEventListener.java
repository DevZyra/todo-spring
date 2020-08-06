package pl.devzyra.todospring.reports;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pl.devzyra.todospring.model.event.TaskDone;
import pl.devzyra.todospring.model.event.TaskEvent;
import pl.devzyra.todospring.model.event.TaskUndone;

@Slf4j
@Service
public class ChangedTaskEventListener {

    private final PersistedTaskEventRepository repository;

    public ChangedTaskEventListener(PersistedTaskEventRepository repository) {
        this.repository = repository;
    }

    @Async
    @EventListener
public void on(TaskDone event){
log.info("Got ~> " + event);
        onChanged(event);
    }

    @Async
    @EventListener
public void off(TaskUndone event){
log.info("Got ~> " + event);
        onChanged(event);
}

    private void onChanged(TaskEvent event) {
        repository.save(new PersistedTaskEvent(event));
    }

}
