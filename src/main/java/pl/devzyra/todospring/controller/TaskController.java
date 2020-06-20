package pl.devzyra.todospring.controller;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pl.devzyra.todospring.model.Task;
import pl.devzyra.todospring.services.TaskService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Slf4j
@RestController
public class TaskController {


    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks/{id}")
    ResponseEntity<Task> getTask(@PathVariable Long id){
        Task task = taskService.findById(id);
        if(task == null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(task);
        }
    }

    @GetMapping(value = "/tasks", params = {"!sort","!page","!size"})
    public ResponseEntity<List<Task>> readAllTasks(){
        log.debug("Exposing all tasks");
        return ResponseEntity.ok(taskService.findAll());
    }

    @GetMapping(value = "/tasks")
    public ResponseEntity<List<Task>> readAllTasks(Pageable pageable){
        log.debug("Pageable controller method");
        return ResponseEntity.ok(taskService.findAll(pageable).getContent());
    }

    @PostMapping("/tasks")
    public ResponseEntity<Task> createTask(@RequestBody @Valid Task task){
        Task result = taskService.save(task);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @Transactional
    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody @Valid Task task){
     if(!taskService.existsById(id)){
         return ResponseEntity.notFound().build();
     }
     Task toUpdate = taskService.findById(id);
     toUpdate.updateFrom(task);
     taskService.save(toUpdate);
     return ResponseEntity.noContent().build();
    }

    @Transactional
    @PatchMapping("/tasks/{id}")
    public ResponseEntity<?> toggleDone(@PathVariable Long id){
        if(!taskService.existsById(id)){
            return ResponseEntity.notFound().build();
        }
       Task task = taskService.findById(id);
        task.setDone(!task.getDone());
        return ResponseEntity.noContent().build();
    }

    // Just an example of previous spring version mappings <4.3 & Response status
    @RequestMapping(method = RequestMethod.DELETE, value = "/tasks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long id){
        taskService.deleteById(id);
    }
}
