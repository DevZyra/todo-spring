package pl.devzyra.todospring.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.devzyra.todospring.model.TaskGroup;
import pl.devzyra.todospring.repositories.TaskGroupRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TaskGroupServiceImpl implements TaskGroupService {

    private final TaskGroupRepository taskGroupRepository;

    public TaskGroupServiceImpl(TaskGroupRepository taskGroupRepository) {
        this.taskGroupRepository = taskGroupRepository;
    }

    @Override
    public List<TaskGroup> findAll() {
        return taskGroupRepository.findAll();
    }

    @Override
    public TaskGroup findById(Long id) {
        Optional<TaskGroup> taskGroup = taskGroupRepository.findById(id);

        if(!taskGroup.isPresent()){
            log.error("TaskGroup [OPTIONAL] is not present");
        }

        return taskGroup.get();
    }

    @Override
    public TaskGroup save(TaskGroup tgEntity) {
        return taskGroupRepository.save(tgEntity);
    }

    @Override
    public boolean existsByDoneIsFalseAndProject_Id(Long projectId) {
        return taskGroupRepository.existsByDoneIsFalseAndProject_Id(projectId);
    }
}
