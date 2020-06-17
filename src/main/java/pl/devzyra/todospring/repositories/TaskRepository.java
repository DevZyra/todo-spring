package pl.devzyra.todospring.repositories;


import org.springframework.data.repository.CrudRepository;
import pl.devzyra.todospring.model.Task;

public interface TaskRepository extends CrudRepository<Task,Long> {
}
