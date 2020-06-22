package pl.devzyra.todospring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.devzyra.todospring.model.Project;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {

    @Override
    @Query("from Project  p join fetch p.steps")
    List<Project> findAll();
}
