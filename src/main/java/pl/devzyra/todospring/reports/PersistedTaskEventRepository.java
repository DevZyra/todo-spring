package pl.devzyra.todospring.reports;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersistedTaskEventRepository extends JpaRepository<PersistedTaskEvent,Long> {

List<PersistedTaskEvent> findByTaskId(Long taskId);


}
