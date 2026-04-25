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
    public ResponseEntity<ErrorResponse> handleAlreadyExists(AlreadyExistsException ex, HttpServletRequest request) {
        log.warn("Already exists: " + ex.getMessage());
        ErrorResponse response = new ErrorResponse();

        response.setPath(request.getRequestURI());
        response.setMessage("Already exists!");
        response.setTimestamp(LocalDateTime.now());

        Map<String, String> details = new HashMap<>();
        details.put("errorType", "ALREADY_EXISTS");
        details.put("info", ex.getMessage());
        response.setDetails(details);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
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


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllException(Exception ex, HttpServletRequest request) {
        log.error("Unhandled exception", ex);
        ErrorResponse response = new ErrorResponse();
        response.setPath(request.getRequestURI());
        response.setMessage("something went wrong");
        response.setTimestamp(LocalDateTime.now());

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }


    @ExceptionHandler(AlreadyDoneBeforeException.class)
    public ResponseEntity<ErrorResponse> handleAlreadyExists(AlreadyDoneBeforeException ex, HttpServletRequest request) {
        log.warn("Already done: " + ex.getMessage());
        ErrorResponse response = new ErrorResponse();

        response.setPath(request.getRequestURI());
        response.setMessage("Already exists!");
        response.setTimestamp(LocalDateTime.now());

        Map<String, String> d = new HashMap<>();
        d.put("info: ", "Already done");
        response.setDetails(d);

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }


    @ExceptionHandler(ApplicationNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleApplicationNotFound(ApplicationNotFoundException ex,
                                                              HttpServletRequest request){

        log.warn("Application not found!");
        ErrorResponse response = new ErrorResponse();
        response.setPath(request.getRequestURI());
        response.setMessage("application not found");
        response.setTimestamp(LocalDateTime.now());

        Map<String, String> d = new HashMap<>();
        d.put("info: ", "Application not found");
        response.setDetails(d);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }


    @ExceptionHandler(CandidateNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCandidateNotFound(CandidateNotFoundException ex,
                                                              HttpServletRequest request){

        log.warn("Candidate not found!");
        ErrorResponse response = new ErrorResponse();
        response.setPath(request.getRequestURI());
        response.setMessage("Candidate not found");
        response.setTimestamp(LocalDateTime.now());

        Map<String, String> d = new HashMap<>();
        d.put("info: ", "Candidate not found");
        response.setDetails(d);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }


    @ExceptionHandler(CompanyNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCompanyNotFound(ApplicationNotFoundException ex,
                                                              HttpServletRequest request){

        log.warn("Company not found!");
        ErrorResponse response = new ErrorResponse();
        response.setPath(request.getRequestURI());
        response.setMessage("Company not found");
        response.setTimestamp(LocalDateTime.now());

        Map<String, String> d = new HashMap<>();
        d.put("info: ", "Company not found");
        response.setDetails(d);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(IncorrectAgeException.class)
    public ResponseEntity<ErrorResponse> handleIncorrectAge(IncorrectAgeException ex,
                                                              HttpServletRequest request){

        log.warn("Incorrect age!");
        ErrorResponse response = new ErrorResponse();
        response.setPath(request.getRequestURI());
        response.setMessage("Incorrect age");
        response.setTimestamp(LocalDateTime.now());

        Map<String, String> d = new HashMap<>();
        d.put("info: ", "Age should be min 16");
        response.setDetails(d);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<ErrorResponse> handleIncorrectPassword(IncorrectPasswordException ex,
                                                      HttpServletRequest request){

        log.warn("Incorrect password!");
        ErrorResponse response = new ErrorResponse();
        response.setPath(request.getRequestURI());
        response.setMessage("Incorrect password");
        response.setTimestamp(LocalDateTime.now());

        Map<String, String> d = new HashMap<>();
        d.put("info: ", "Password is not correct!");
        response.setDetails(d);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(JobPostingNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleJobPostingNotFound(JobPostingNotFoundException ex, HttpServletRequest request) {
        log.warn("Not Found: " + ex.getMessage());
        ErrorResponse response = new ErrorResponse();

        response.setPath(request.getRequestURI());
        response.setMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());

        Map<String, String> d = new HashMap<>();
        d.put("info", "Job Post not found");
        response.setDetails(d);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }


    @ExceptionHandler(MismatchCompanyApplicationException.class)
    public ResponseEntity<ErrorResponse> handleMismatchCompanyApplication(MismatchCompanyApplicationException ex, HttpServletRequest request) {
        log.warn("Mismatch Company: " + ex.getMessage());
        ErrorResponse response = new ErrorResponse();

        response.setPath(request.getRequestURI());
        response.setMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());

        Map<String, String> d = new HashMap<>();
        d.put("info", "Mismatch Company");
        response.setDetails(d);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException ex, HttpServletRequest request) {
        log.warn("User not found: " + ex.getMessage());
        ErrorResponse response = new ErrorResponse();

        response.setPath(request.getRequestURI());
        response.setMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());

        Map<String, String> d = new HashMap<>();
        d.put("info", "User not found");
        response.setDetails(d);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }






}
