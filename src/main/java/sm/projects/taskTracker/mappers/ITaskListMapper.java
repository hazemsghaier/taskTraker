package sm.projects.taskTracker.mappers;

import sm.projects.taskTracker.domain.DTO.TaskListDTO;
import sm.projects.taskTracker.domain.entitys.TaskList;

public interface ITaskListMapper {
    TaskList fromDto(TaskListDTO dto);
    TaskListDTO toDto(TaskList taskList);
}
