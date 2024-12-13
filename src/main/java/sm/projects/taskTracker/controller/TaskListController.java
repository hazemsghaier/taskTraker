package sm.projects.taskTracker.controller;

import org.springframework.web.bind.annotation.*;
import sm.projects.taskTracker.domain.DTO.TaskListDTO;
import sm.projects.taskTracker.domain.entitys.TaskList;
import sm.projects.taskTracker.mappers.ITaskListMapper;
import sm.projects.taskTracker.mappers.impl.TaskListMapperImpl;
import sm.projects.taskTracker.service.ITaskListService;
import sm.projects.taskTracker.service.impl.TaskListServiceImpl;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/task-lists")
public class TaskListController {
    private  final TaskListServiceImpl taskListService;
    private final TaskListMapperImpl taskListMapper;
    public TaskListController(TaskListServiceImpl taskListService, TaskListMapperImpl taskListMapper) {
        this.taskListService = taskListService;
        this.taskListMapper = taskListMapper;
    }
    @GetMapping
    public List<TaskListDTO> listtaskList(){

        return taskListService.listTaskLists().stream().map(taskListMapper::toDto).toList();
    }
    @PostMapping
    public TaskListDTO createTaskList(@RequestBody TaskListDTO taskListDTO){
        var createdtaskList=taskListService.createTaskList(taskListMapper.fromDto(taskListDTO));
        return taskListMapper.toDto(createdtaskList);
    }
    @GetMapping(path="/{task_list_id}")
    public TaskListDTO getTaskList(@PathVariable("task_list_id") UUID id){
        return taskListService.getTaskList(id).map(taskListMapper::toDto).orElse(null);
    }
    @PutMapping(path = "/{task_list_id}")
    public TaskListDTO updateTaskList(@PathVariable("task_list_id") UUID id, @RequestBody TaskListDTO taskListDTO){
        TaskList updatedTaskList = taskListService.updateTaskList(id, taskListMapper.fromDto(taskListDTO));
        return taskListMapper.toDto(updatedTaskList);
    }
    @DeleteMapping(path="/{task_list_id}")
    public void deleteTaskList(@PathVariable("task_list_id") UUID id){
        taskListService.deleteTaskList(id);
    }
}
