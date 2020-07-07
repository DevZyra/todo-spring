package pl.devzyra.todospring.services;


import pl.devzyra.todospring.model.Project;
import pl.devzyra.todospring.model.Task;
import pl.devzyra.todospring.model.TaskGroup;
import pl.devzyra.todospring.model.projection.GroupReadModel;
import pl.devzyra.todospring.model.projection.GroupWriteModel;

import java.util.List;
import java.util.Optional;

interface TaskGroupService {

    List<TaskGroup> findAll();

    TaskGroup findById(Long id);

    TaskGroup save(TaskGroup tgEntity);

    boolean existsByDoneIsFalseAndProject_Id(Long projectId);

    GroupReadModel createGroup(GroupWriteModel source);

    List<GroupReadModel> readAll();

    void toggleGroup(Long groupId);

    List<Task> findAllByGroup_Id(Long groupId);

    GroupReadModel createGroup(GroupWriteModel source, Project project);
}
