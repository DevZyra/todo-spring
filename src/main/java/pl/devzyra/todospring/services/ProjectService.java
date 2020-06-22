package pl.devzyra.todospring.services;


import pl.devzyra.todospring.model.Project;

import java.util.List;

public interface ProjectService {

   List<Project> findAll();

   Project findById(Long id);

    Project save(Project projectEntity);
}
