package sm.projects.taskTracker.service;

import sm.projects.taskTracker.domain.entitys.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ITaskService {
    List<Task> listTasks(UUID taskListId);
    Task createTask(UUID taskListId,Task task);
    Optional<Task> getTask(UUID taskListId, UUID taskId);
    Task updateTask(UUID taskListId, UUID taskId, Task task);
    void deleteTask(UUID taskListId, UUID taskId);
}
