package pl.devzyra.todospring.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pl.devzyra.todospring.model.Task;
import pl.devzyra.todospring.model.TaskGroup;
import pl.devzyra.todospring.model.projection.GroupReadModel;
import pl.devzyra.todospring.model.projection.GroupTaskReadModel;
import pl.devzyra.todospring.model.projection.GroupWriteModel;
import pl.devzyra.todospring.repositories.TaskRepository;
import pl.devzyra.todospring.services.TaskGroupServiceImpl;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/groups")
public class TaskGroupController {


    TaskGroupServiceImpl taskGroupService;
    TaskRepository taskRepository;

    public TaskGroupController(TaskGroupServiceImpl taskGroupService, TaskRepository taskRepository) {
        this.taskGroupService = taskGroupService;
        this.taskRepository = taskRepository;
    }

    @GetMapping
    ResponseEntity<List<GroupReadModel>> readAllGroups(){
        List<GroupReadModel> result = taskGroupService.readAll();

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping
    ResponseEntity<GroupReadModel> createGroup(@RequestBody @Valid GroupWriteModel toCreate){
        GroupReadModel result = taskGroupService.createGroup(toCreate);
        return ResponseEntity.created(URI.create("/")).body(result);
    }

    @GetMapping("/{id}")
    ResponseEntity<List<Task>> readAllTasksFromGroup(@PathVariable Long id){
        return ResponseEntity.ok(taskGroupService.findAllByGroup_Id(id));
    }

    @Transactional
    @PatchMapping("/{groupId}")
    public ResponseEntity<?> toggleGroup(@PathVariable(name = "groupId") Long id){
        taskGroupService.toggleGroup(id);
        return ResponseEntity.noContent().build();
    }
}
