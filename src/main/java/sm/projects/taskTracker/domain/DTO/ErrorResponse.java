package sm.projects.taskTracker.domain.DTO;

import java.io.Serializable;

public record ErrorResponse(int status, String message,String details) implements Serializable {
}
