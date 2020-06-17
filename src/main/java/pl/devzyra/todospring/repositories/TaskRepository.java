package pl.devzyra.todospring.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import pl.devzyra.todospring.model.Task;

@RepositoryRestResource
public interface TaskRepository extends CrudRepository<Task,Long> {
}
