package com.example.hirehub.exception;

public class JobPostingNotFoundException extends RuntimeException{
    public JobPostingNotFoundException(String message) {
        super(message);
    }
}
