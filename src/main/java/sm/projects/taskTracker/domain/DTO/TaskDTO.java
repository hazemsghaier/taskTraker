package sm.projects.taskTracker.domain.DTO;

import sm.projects.taskTracker.domain.entitys.TaskPriority;
import sm.projects.taskTracker.domain.entitys.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDTO(
        UUID id,
        String title,
        String description,
        LocalDateTime dueDate,
        TaskStatus status,
        TaskPriority priority

) {
}
