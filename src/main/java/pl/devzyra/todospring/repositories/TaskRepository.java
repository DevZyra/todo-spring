package pl.devzyra.todospring.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import pl.devzyra.todospring.model.Task;

import java.util.List;

@Repository // RestResource
public interface TaskRepository extends JpaRepository<Task,Long> {


/*    @Override
    @RestResource(exported = false) // Excluding usage of delete request on RestRepository
    void deleteById(Long aLong);

    @Override
    @RestResource(exported = false)
    void delete(Task task);

    @RestResource(path = "done", rel = "done")*/
    List<Task> findByDone(@Param("state") Boolean done);
}
