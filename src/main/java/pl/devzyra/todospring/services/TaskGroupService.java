package pl.devzyra.todospring.services;


import pl.devzyra.todospring.model.TaskGroup;

import java.util.List;
import java.util.Optional;

interface TaskGroupService {

    List<TaskGroup> findAll();

    TaskGroup findById(Long id);

    TaskGroup save(TaskGroup tgEntity);

    boolean existsByDoneIsFalseAndProject_Id(Long projectId);
}
