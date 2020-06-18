package pl.devzyra.todospring.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import pl.devzyra.todospring.model.Task;

import java.util.List;


public interface TaskService {

    List<Task> findAll();

    Page<Task> findAll(Pageable pageable);

    Task findById(Long id);

    Task save(Task task);

    List<Task> findByDone(@Param("state") Boolean done);

    Boolean existsById(Long id);

}
