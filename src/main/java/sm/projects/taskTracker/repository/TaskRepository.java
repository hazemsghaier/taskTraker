package sm.projects.taskTracker.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sm.projects.taskTracker.domain.entitys.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
    List<Task> findByTaskListId(UUID projectId);
    Optional<Task> findByTaskListIdAndId(UUID taskListId,UUID id);
    void deleteByTaskListIdAndId(UUID taskListId,UUID id);
}
