package pl.devzyra.todospring.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import pl.devzyra.todospring.model.Task;


import java.util.List;
import java.util.Optional;

@Repository // @RepositoryRestResource
public interface TaskRepository extends JpaRepository<Task,Long> {


/*    @Override
    @RestResource(exported = false) // Excluding usage of delete request on RestRepository
    void deleteById(Long aLong);

    @Override
    @RestResource(exported = false)
    void delete(Task task);
*/
    @RestResource(path = "done", rel = "done")
    List<Task> findByDone(@Param("state") Boolean done);

    @Override
    @Query(nativeQuery = true,value = "select count(*) > 0 from tasks where id=?1")
    boolean existsById(Long id);

    // Example of different technique to pass param to native query
    @Override
    @Query(nativeQuery = true,value = "select * from tasks where id=:id")
    Optional<Task> findById(@Param("id") Long id);


    boolean existsByDoneIsFalseAndGroup_Id(Long groupId);


}
