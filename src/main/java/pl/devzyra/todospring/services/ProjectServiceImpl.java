package pl.devzyra.todospring.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.devzyra.todospring.model.Project;
import pl.devzyra.todospring.repositories.ProjectRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
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
}
