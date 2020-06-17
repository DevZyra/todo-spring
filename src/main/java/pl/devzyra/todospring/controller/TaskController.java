package pl.devzyra.todospring.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import pl.devzyra.todospring.repositories.TaskRepository;

@Slf4j
@RepositoryRestController
public class TaskController {


    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/tasks")
    public ResponseEntity<?> readAllTasks(){
        log.debug("Exposing all tasks");
        return ResponseEntity.ok(taskRepository.findAll());
    }

}
