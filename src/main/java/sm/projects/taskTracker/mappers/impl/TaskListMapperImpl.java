package sm.projects.taskTracker.mappers.impl;
import org.springframework.stereotype.Component;
import sm.projects.taskTracker.domain.DTO.TaskListDTO;
import sm.projects.taskTracker.domain.entitys.Task;
import sm.projects.taskTracker.domain.entitys.TaskList;
import sm.projects.taskTracker.domain.entitys.TaskStatus;
import sm.projects.taskTracker.mappers.ITaskListMapper;

import java.util.List;
import java.util.Optional;
@Component
public class TaskListMapperImpl implements ITaskListMapper {
    private  final  TaskMapperImpl taskMapper;
    public TaskListMapperImpl(TaskMapperImpl taskMapper) {
        this.taskMapper = taskMapper;
    }
    @Override
    //transforme the dto object to an entity
    public TaskList fromDto(TaskListDTO dto){
        return new TaskList(dto.id(), dto.title(), dto.description(), Optional.ofNullable(dto.tasks()).map(tasks->tasks.stream().map(taskMapper::fromDTO).toList()).orElse(null),null,null);
    }
    @Override
    public TaskListDTO toDto(TaskList taskList){
        return new TaskListDTO(
                taskList.getId(),
                taskList.getTitle(),
                taskList.getDescription(),
                Optional.ofNullable(taskList.getTasks()).map(List::size).orElse(0),
                calculateTaskListProgress(taskList.getTasks()),
                Optional.ofNullable(taskList.getTasks()).map(tasks->tasks.stream().map(taskMapper::toDTO).toList()).orElse(null)

        );
    }
    private double calculateTaskListProgress( List<Task> tasks){
         if(null==tasks){
             return 0.0;
         }
         long taskCompleted=tasks.stream().filter(task -> TaskStatus.CLOSED==task.getTaskStatus()).count();
         return  (double) taskCompleted /tasks.size();
    }

}
