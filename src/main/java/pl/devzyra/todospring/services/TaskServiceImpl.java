package pl.devzyra.todospring.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.devzyra.todospring.model.Task;
import pl.devzyra.todospring.repositories.TaskRepository;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Page<Task> findAll(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }

    @Override
    public Task findById(Long id) {
        Optional<Task> taskOptional = taskRepository.findById(id);

        if(!taskOptional.isPresent()){
            log.error("Task [OPTIONAL] is not present");
        }

        return taskOptional.get();
    }

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public List<Task> findByDone(Boolean done) {
        return taskRepository.findByDone(done);
    }

    @Override
    public Boolean existsById(Long id) {
        return taskRepository.existsById(id);
    }

    @Override
    public boolean existsByDoneIsFalseAndGroup_Id(Long groupId) {
      return taskRepository.existsByDoneIsFalseAndGroup_Id(groupId);
    }

    @Override
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
        log.warn("Task has been deleted");
    }

}
