package sm.projects.taskTracker.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import sm.projects.taskTracker.domain.entitys.Task;
import sm.projects.taskTracker.domain.entitys.TaskList;
import sm.projects.taskTracker.domain.entitys.TaskPriority;
import sm.projects.taskTracker.domain.entitys.TaskStatus;
import sm.projects.taskTracker.repository.TaskListRepository;
import sm.projects.taskTracker.repository.TaskRepository;
import sm.projects.taskTracker.service.ITaskService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements ITaskService {
    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;

    public TaskServiceImpl(TaskRepository taskRepository, TaskListRepository taskListRepository) {
        this.taskRepository = taskRepository;
        this.taskListRepository = taskListRepository;
    }
    @Override
   public List<Task> listTasks(UUID taskListId){
        return taskRepository.findByTaskListId(taskListId);
    }
    @Override
    public Task createTask(UUID taskListId,Task task){

          if(taskListId == null){
              throw new IllegalArgumentException("every task must be in a task list");
          }
          if(null!=task.getId()){
              throw new IllegalArgumentException("the task dosen t need a id when it is created");
          }
          if(null==task.getTitle()||task.getTitle().isBlank()){
              throw new IllegalArgumentException("the title need to be set");
          }
        TaskPriority taskPriority= Optional.ofNullable(task.getTaskPriority()).orElse(TaskPriority.MEDIUM);
        TaskStatus taskStatus=TaskStatus.OPEN;
       TaskList taskList= taskListRepository.findById(taskListId).orElseThrow(()->new IllegalArgumentException("the task list does not exist"));
        LocalDateTime now=LocalDateTime.now();
        return taskRepository.save(new Task(null,task.getTitle(),task.getDescription(),taskList,task.getDueDate(),taskStatus,taskPriority,now,now));
    }
    @Override
    public Optional<Task> getTask(UUID taskListId, UUID taskId){
        return  taskRepository.findByTaskListIdAndId(taskListId, taskId);
    }
    public Task updateTask(UUID taskListId, UUID taskId, Task task){
        if(task.getId()== null){
            throw new IllegalArgumentException("every task must be in a task list");
        }
        if(!Objects.equals(taskId, task.getId())){
            throw new IllegalArgumentException("the same id must be set");
        }
        if(null==task.getTitle()||task.getTitle().isBlank()){
            throw new IllegalArgumentException("the title need to be set");
        }
        System.out.println(task.toString());
        if(null==task.getTaskPriority()){

            throw new IllegalArgumentException("the task must have a priority");
        }
        Task existingTask=  taskRepository.findByTaskListIdAndId(taskListId, taskId).orElseThrow(()->new IllegalArgumentException("the task does not exist"));
        System.out.println(existingTask.toString());
        existingTask.setUpdated(LocalDateTime.now());
        existingTask.setTaskStatus(task.getTaskStatus());
        existingTask.setTaskPriority(task.getTaskPriority());
        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setDueDate(task.getDueDate());
        return taskRepository.save(existingTask);

    }
    @Transactional

    @Override
    public  void deleteTask(UUID taskId , UUID taskListId){
        System.out.println("1111111111111111111111111");
        taskRepository.deleteByTaskListIdAndId(taskListId,taskId);
    }

}
