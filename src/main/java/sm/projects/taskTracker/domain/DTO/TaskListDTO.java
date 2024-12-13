package sm.projects.taskTracker.domain.DTO;

import java.util.List;
import java.util.UUID;

public record TaskListDTO(
        UUID id,
        String title,
        String description,
        Integer count,
        Double progress,
        List<TaskDTO> tasks
) {
}
