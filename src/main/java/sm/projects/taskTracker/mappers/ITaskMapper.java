package sm.projects.taskTracker.mappers;

import sm.projects.taskTracker.domain.DTO.TaskDTO;
import sm.projects.taskTracker.domain.entitys.Task;

public interface ITaskMapper {
    Task fromDTO(TaskDTO taskDTO);
    TaskDTO toDTO(Task task);
}
