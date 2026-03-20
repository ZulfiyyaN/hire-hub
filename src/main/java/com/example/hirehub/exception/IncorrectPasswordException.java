package com.example.hirehub.exception;

public class IncorrectPasswordException  extends RuntimeException{
    public IncorrectPasswordException(String message) {
        super(message);
    }
}
