package com.intentsg.model.exeptions;

public class AlreadyExistsExeption extends RuntimeException {

    public AlreadyExistsExeption() {
    }

    public AlreadyExistsExeption(String message) {
        super(message);
    }
}
