package sm.projects.taskTracker.mappers.impl;

import org.springframework.stereotype.Component;
import sm.projects.taskTracker.domain.DTO.TaskDTO;
import sm.projects.taskTracker.domain.entitys.Task;
import sm.projects.taskTracker.mappers.ITaskMapper;
@Component
public class TaskMapperImpl implements ITaskMapper {
    @Override
    public Task fromDTO(TaskDTO taskDTO){
        System.out.println(taskDTO.status());
        return new Task(taskDTO.id(), taskDTO.title(), taskDTO.description(),null, taskDTO.dueDate(),taskDTO.status(),taskDTO.priority(),null,null);
    }
    @Override
    public TaskDTO toDTO(Task task){
        return new TaskDTO(task.getId(), task.getTitle(), task.getDescription(),task.getDueDate(),task.getTaskStatus(),task.getTaskPriority());
    }
}
