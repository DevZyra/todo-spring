package pl.devzyra.todospring.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.devzyra.todospring.model.Task;
import pl.devzyra.todospring.model.TaskGroup;
import pl.devzyra.todospring.model.projection.GroupReadModel;
import pl.devzyra.todospring.model.projection.GroupWriteModel;
import pl.devzyra.todospring.repositories.TaskGroupRepository;
import pl.devzyra.todospring.repositories.TaskRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TaskGroupServiceImpl implements TaskGroupService {

    private final TaskGroupRepository taskGroupRepository;
    private final TaskRepository taskRepository;

    public TaskGroupServiceImpl(TaskGroupRepository taskGroupRepository, TaskRepository taskRepository) {
        this.taskGroupRepository = taskGroupRepository;
        this.taskRepository = taskRepository;
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

    @Override
    public GroupReadModel createGroup(final GroupWriteModel source) {
       TaskGroup tg = taskGroupRepository.save(source.toGroup());
       return new GroupReadModel(tg);
    }

    @Override
    public List<GroupReadModel> readAll() {
     return taskGroupRepository.findAll().stream().map(GroupReadModel::new).collect(Collectors.toList());
    }

    @Override
    public void toggleGroup(Long groupId) {
        if(taskRepository.existsByDoneIsFalseAndGroup_Id(groupId)){
            throw new IllegalStateException("Cannot close group : Must complete tasks first.");
        }
      TaskGroup result = taskGroupRepository.findById(groupId).orElseThrow(()->new IllegalArgumentException("TaskGroup does not exist."));
        result.setDone(!result.getDone());
        taskGroupRepository.save(result);
    }

    @Override
    public List<Task> findAllByGroup_Id(Long groupId) {
        return taskRepository.findAllByGroup_Id(groupId);
    }

}
