package sm.projects.taskTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sm.projects.taskTracker.domain.entitys.TaskList;

import java.util.UUID;
@Repository
public interface TaskListRepository extends JpaRepository<TaskList, UUID> {
}
