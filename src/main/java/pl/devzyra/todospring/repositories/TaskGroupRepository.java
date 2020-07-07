package pl.devzyra.todospring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.devzyra.todospring.model.TaskGroup;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskGroupRepository extends JpaRepository<TaskGroup,Long> {


    // HQL -> Hibernate query language, prevents from N+1 selects on DB
    @Override
    @Query("select distinct g from TaskGroup g join fetch g.tasks")  // previous "from TaskGroup g join fetch g.tasks"
    List<TaskGroup> findAll();


    boolean existsByDoneIsFalseAndProject_Id(Long projectId);

}
