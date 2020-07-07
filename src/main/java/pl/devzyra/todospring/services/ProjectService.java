package pl.devzyra.todospring.services;


import pl.devzyra.todospring.model.Project;
import pl.devzyra.todospring.model.projection.GroupReadModel;
import pl.devzyra.todospring.model.projection.ProjectWriteModel;

import java.time.LocalDateTime;
import java.util.List;

public interface ProjectService {

   List<Project> findAll();

   Project findById(Long id);

    Project save(ProjectWriteModel projectEntity);

    GroupReadModel createGroup(LocalDateTime deadline, Long projectId);
}
