package sm.projects.taskTracker.service.impl;

import org.springframework.stereotype.Service;
import sm.projects.taskTracker.domain.entitys.Task;
import sm.projects.taskTracker.domain.entitys.TaskList;
import sm.projects.taskTracker.repository.TaskListRepository;
import sm.projects.taskTracker.service.ITaskListService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskListServiceImpl implements ITaskListService {
    private final TaskListRepository taskListRepository;
    public TaskListServiceImpl(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }
    @Override
    public List<TaskList> listTaskLists() {
        return taskListRepository.findAll();
    }
    @Override
    public TaskList createTaskList(TaskList taskList) {
        if(null!=taskList.getId()){
            throw new IllegalArgumentException("the task list already have an ID");
        }
        if(null==taskList.getTitle() || "".equals(taskList.getTitle())){
            throw new IllegalArgumentException("the task list must have a title");
        }
        LocalDateTime now =LocalDateTime.now();
        return  taskListRepository.save(new TaskList(null,taskList.getTitle(),taskList.getDescription(),null,now,now));
    }
    @Override
    public Optional<TaskList> getTaskList(UUID id){
        return taskListRepository.findById(id);
    }
    @Override
    public TaskList updateTaskList(UUID taskListId, TaskList taskList){
        if(null==taskList.getId()){
            throw new IllegalArgumentException("the task list must have an ID");
        }

        TaskList oldTaskList = taskListRepository.findById(taskListId).orElseThrow(()->new IllegalArgumentException("the id of the tas list dosen t exist"));
        oldTaskList.setTitle(taskList.getTitle());
        oldTaskList.setDescription(taskList.getDescription());
        oldTaskList.setUpdated(LocalDateTime.now());
        return taskListRepository.save(oldTaskList);
    }
    @Override
    public void deleteTaskList(UUID taskListId){
        if(null==taskListId){
            throw new IllegalArgumentException("the task list must have an ID");
        }
         taskListRepository.deleteById(taskListId);


    }



}
