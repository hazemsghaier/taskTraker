package sm.projects.taskTracker.domain.entitys;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "Id",updatable = false, nullable = false)
    private UUID id;
    @Column(name = "title", nullable = false)

    private String title;
    @Column(name="description")
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_list_id")
    private TaskList taskList;
    @Column(name = "due_date")
    private LocalDateTime dueDate;
    @Column(name="status",nullable = false)
    private TaskStatus taskStatus;
    @Column(name = "priority",nullable = false)
    private TaskPriority taskPriority;
    @Column(name = "created",nullable = false)
    private LocalDateTime created;
    @Column(name = "updated",nullable = false)
    private LocalDateTime updated;

    public Task() {
    }

    public Task(UUID id, String title, String description, TaskList taskList, LocalDateTime dueDate, TaskStatus taskStatus, TaskPriority taskPriority, LocalDateTime created, LocalDateTime updated) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.taskList = taskList;
        this.dueDate = dueDate;
        this.taskStatus = taskStatus;
        this.taskPriority = taskPriority;
        this.created = created;
        this.updated = updated;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public TaskPriority getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(TaskPriority taskPriority) {
        this.taskPriority = taskPriority;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) && Objects.equals(title, task.title) && Objects.equals(description, task.description) && Objects.equals(taskList, task.taskList) && Objects.equals(dueDate, task.dueDate) && taskStatus == task.taskStatus && taskPriority == task.taskPriority && Objects.equals(created, task.created) && Objects.equals(updated, task.updated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, taskList, dueDate, taskStatus, taskPriority, created, updated);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", taskList=" + taskList +
                ", dueDate=" + dueDate +
                ", taskStatus=" + taskStatus +
                ", taskPriority=" + taskPriority +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}