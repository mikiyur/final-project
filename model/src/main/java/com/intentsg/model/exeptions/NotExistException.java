package com.intentsg.model.exeptions;

public class NotExistException extends RuntimeException {

    public NotExistException() {
    }

    public NotExistException(String message) {
        super(message);
    }
}
