package pl.devzyra.todospring.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.devzyra.todospring.config.TaskConfigProperties;
import pl.devzyra.todospring.model.Project;
import pl.devzyra.todospring.model.Task;
import pl.devzyra.todospring.model.TaskGroup;
import pl.devzyra.todospring.model.projection.GroupReadModel;
import pl.devzyra.todospring.model.projection.GroupTaskWriteModel;
import pl.devzyra.todospring.model.projection.GroupWriteModel;
import pl.devzyra.todospring.model.projection.ProjectWriteModel;
import pl.devzyra.todospring.repositories.ProjectRepository;
import pl.devzyra.todospring.repositories.TaskGroupRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final TaskGroupRepository taskGroupRepository;
    private final TaskConfigProperties taskConfigProperties;
    private final TaskGroupService taskGroupService;

    public ProjectServiceImpl(ProjectRepository projectRepository, TaskGroupRepository taskGroupRepository, TaskGroupService taskGroupService, TaskConfigProperties taskConfigProperties) {
        this.projectRepository = projectRepository;
        this.taskGroupRepository = taskGroupRepository;
        this.taskGroupService = taskGroupService;
        this.taskConfigProperties = taskConfigProperties;
    }

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public Project findById(Long id) {

        Optional<Project> project = projectRepository.findById(id);

        if(!project.isPresent()){
            log.error("Project [OPTIONAL] is not present");
        }

        return project.get();
    }

    @Override
    public Project save(ProjectWriteModel projectEntity) {
        return projectRepository.save(projectEntity.toProject());
    }

    // Im not a fan of how this method is implemented within the course although this is only demonstrational purpose
    // for revision of Spring concepts so Im not gonna waste time to figure it out differently

    // Also I'm not sure if this method shouldn't be @Transactional
    @Override
    public GroupReadModel createGroup(LocalDateTime deadline, Long projectId) {
        if (!taskConfigProperties.getTemplate().getAllowMultipleTasks() && taskGroupRepository.existsByDoneIsFalseAndProject_Id(projectId)) {
            throw new IllegalStateException("Only one undone group from project is allowed");
        }
        return projectRepository.findById(projectId)
                .map(project -> {
                    var targetGroup = new GroupWriteModel();
                    targetGroup.setDescription(project.getDescription());
                    targetGroup.setTasks(
                            project.getSteps().stream()
                                    .map(projectStep -> {
                                         var task =  new GroupTaskWriteModel();
                                            task.setDescription(projectStep.getDescription());
                            task.setDeadline(deadline.plusDays(projectStep.getDaysToDeadline()));
                                    return task;
                                    }
                                    ).collect(Collectors.toSet()));
                    return taskGroupService.createGroup(targetGroup, project);
                }).orElseThrow(() -> new IllegalArgumentException("Project with given id not found"));
    }


}
