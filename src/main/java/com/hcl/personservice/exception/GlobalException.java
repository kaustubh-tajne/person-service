package com.hcl.personservice.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.Null;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

@RestControllerAdvice
public class GlobalException {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalException.class.getName());

    /*

    // Old Way
    @ExceptionHandler(EntityNotFoundException.class)
//    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException exception) {
    public ResponseEntity<APIError> handleEntityNotNotFoundException(EntityNotFoundException exception) {
        LOGGER.error(exception.getMessage(), exception);
//        return ResponseEntity.of(Optional.of(exception.getMessage())); // without status code just string
//        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND); // with status code
        APIError apiError = new APIError();
        apiError.setErrorCode("NOT EXISTS");
        apiError.setErrorDescription("Not Found");
        apiError.setHttpStatusCode("404");
        apiError.setHttpStatusDescription("NOT FOUND");
        apiError.setReason(exception.getMessage());
        return ResponseEntity.of(Optional.of(apiError));
    }
     */

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleEntityNotFoundException(EntityNotFoundException exception) {
        LOGGER.error(exception.getMessage(), exception);
        final ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getMessage());
        problemDetail.setTitle("Server Error");
        problemDetail.setProperty("reason", exception.getMessage());
//        problemDetail.setProperty("rootCause", exception.getCause());
        problemDetail.setProperty("rootCause", exception.getStackTrace());
        return ResponseEntity.of(problemDetail).build();
    }

    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<ProblemDetail> handlePersonNotFoundException(PersonNotFoundException exception) {
        LOGGER.error(exception.getMessage(), exception);
        final ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage());
        problemDetail.setProperty("id", exception.getId());
        problemDetail.setProperty("exception", exception.getClass().getName());
        return ResponseEntity.of(problemDetail).build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        LOGGER.error(exception.getMessage(), exception);
        final ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage());
//        problemDetail.setProperty("exception", exception.getClass().getName());
        for (final FieldError fieldError: exception.getFieldErrors()) {
            problemDetail.setProperty(fieldError.getField(), fieldError.getRejectedValue());
        }
        return ResponseEntity.of(problemDetail).build();
    }

//    @ExceptionHandler(NullPointerException.class)
//    public void handleNullPointerException(NullPointerException exception) {
//        LOGGER.error(exception.getMessage());
//    }

}
