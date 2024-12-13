package sm.projects.taskTracker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import sm.projects.taskTracker.domain.DTO.ErrorResponse;

@ControllerAdvice
public class GlobalExeptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleException(RuntimeException ex, WebRequest request) {
        ErrorResponse error=new ErrorResponse(HttpStatus.BAD_REQUEST.value(),ex.getMessage(),request.getDescription(false));
      return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
}
