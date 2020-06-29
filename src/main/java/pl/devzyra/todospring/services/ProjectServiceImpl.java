package pl.devzyra.todospring.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.devzyra.todospring.config.TaskConfigProperties;
import pl.devzyra.todospring.model.Project;
import pl.devzyra.todospring.model.Task;
import pl.devzyra.todospring.model.TaskGroup;
import pl.devzyra.todospring.model.projection.GroupReadModel;
import pl.devzyra.todospring.repositories.ProjectRepository;
import pl.devzyra.todospring.repositories.TaskGroupRepository;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final TaskGroupRepository taskGroupRepository;
    private final TaskConfigProperties taskConfigProperties;

    public ProjectServiceImpl(ProjectRepository projectRepository, TaskGroupRepository taskGroupRepository, TaskConfigProperties taskConfigProperties) {
        this.projectRepository = projectRepository;
        this.taskGroupRepository = taskGroupRepository;
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
    public Project save(Project projectEntity) {
        return projectRepository.save(projectEntity);
    }

    @Override
    public GroupReadModel createGroup(LocalDateTime deadline, Long projectId) {
        if (!taskConfigProperties.getTemplate().getAllowMultipleTasks() && taskGroupRepository.existsByDoneIsFalseAndProject_Id(projectId)) {
            throw new IllegalStateException("Only one undone group from project is allowed");
        }
        TaskGroup result = projectRepository.findById(projectId)
                .map(project -> {
                    var targetGroup = new TaskGroup();
                    targetGroup.setDescription(project.getDescription());
                    targetGroup.setTasks(
                            project.getSteps().stream()
                                    .map(projectStep -> new Task(
                                            projectStep.getDescription(),
                                            deadline.plusDays(projectStep.getDaysToDeadline()))
                                    ).collect(Collectors.toSet())
                    );
                    return taskGroupRepository.save(targetGroup);
                }).orElseThrow(() -> new IllegalArgumentException("Project with given id not found"));
        return new GroupReadModel(result);
    }


}
