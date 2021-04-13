package com.intentsg.model.exeptions;

public class WrongUserNameExeption extends RuntimeException{

    public WrongUserNameExeption() {
    }

    public WrongUserNameExeption(String message) {
        super(message);
    }
}
