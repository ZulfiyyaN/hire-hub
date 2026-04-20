package com.example.hirehub.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.boot.web.error.Error;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(AlreadyExistsException.class)
    public ErrorResponse handleAlreadyExists(AlreadyExistsException ex, HttpServletRequest request) {
        log.warn("Already exists: " + ex.getMessage());
        ErrorResponse response = new ErrorResponse();

        response.setPath(request.getRequestURI());
        response.setMessage("Already exists!");
        response.setTimestamp(LocalDateTime.now());

        return response;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleNotValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> details = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                details.put(error.getField(), error.getDefaultMessage())
        );

        ErrorResponse response = new ErrorResponse();

        response.setPath(request.getRequestURI());
        response.setMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());
        response.setDetails(details);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


    @ExceptionHandler(JobPostingNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleJobPostingNotFound(JobPostingNotFoundException ex, HttpServletRequest request){
        log.warn("Not Found: " + ex.getMessage());
        ErrorResponse response = new ErrorResponse();

        response.setPath(request.getRequestURI());
        response.setMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }




    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleallException (Exception ex, HttpServletRequest request){
        log.error("Unhandled exception", ex);
        ErrorResponse response = new ErrorResponse();
        response.setPath(request.getRequestURI());
        response.setMessage("something went wrong");
        response.setTimestamp(LocalDateTime.now());

        return  ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }


}
