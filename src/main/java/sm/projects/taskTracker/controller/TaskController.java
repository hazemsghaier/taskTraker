package sm.projects.taskTracker.controller;

import org.springframework.web.bind.annotation.*;
import sm.projects.taskTracker.domain.DTO.TaskDTO;
import sm.projects.taskTracker.domain.entitys.Task;
import sm.projects.taskTracker.mappers.impl.TaskMapperImpl;
import sm.projects.taskTracker.service.impl.TaskServiceImpl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/task-lists/{task_list_id}/tasks")
public class TaskController
{
    private final TaskServiceImpl taskService;
    private final TaskMapperImpl taskMapper;
    public TaskController(TaskServiceImpl taskService, TaskMapperImpl taskMapper){
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }
    @GetMapping
    public List<TaskDTO> listTaskes(@PathVariable UUID task_list_id){
        return taskService.listTasks(task_list_id).stream().map(taskMapper::toDTO).toList();
    }
    @PutMapping(path ="/{task_id}")
    public TaskDTO updateTask(@PathVariable("task_list_id") UUID task_list_id,@PathVariable("task_id") UUID task_id,@RequestBody TaskDTO taskDto){
        Task task=taskService.updateTask(task_list_id,task_id,taskMapper.fromDTO(taskDto));
        return taskMapper.toDTO(task);
    }
    @DeleteMapping(path="/{task_id}")
    public void deleteTask(@PathVariable("task_id") UUID task_id,@PathVariable UUID task_list_id){
        taskService.deleteTask(task_id,task_list_id);
    }
    @PostMapping
    public TaskDTO createTask(@PathVariable UUID task_list_id,@RequestBody TaskDTO taskDto){
        Task task=taskService.createTask(task_list_id,taskMapper.fromDTO(taskDto));
        return taskMapper.toDTO(task);
    }
    @GetMapping(path = "/{task_id}")
    public TaskDTO getTask(@PathVariable("task_list_id") UUID task_list_id,@PathVariable("task_id") UUID task_id){
        return taskService.getTask(task_list_id,task_id).map(taskMapper::toDTO).orElse(null);
    }


}
