package com.example.taskflow.exception;

public class DoesNoExistException extends RuntimeException {

    public DoesNoExistException(String message) {
        super(message);
    }

}
