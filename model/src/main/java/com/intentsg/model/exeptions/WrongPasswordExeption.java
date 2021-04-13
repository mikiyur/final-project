package com.intentsg.model.exeptions;

public class WrongPasswordExeption extends RuntimeException {

    public WrongPasswordExeption() {
    }

    public WrongPasswordExeption(String message) {
        super(message);
    }
}
