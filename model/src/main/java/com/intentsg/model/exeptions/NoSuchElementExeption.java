package com.intentsg.model.exeptions;

public class NoSuchElementExeption extends RuntimeException {

    public NoSuchElementExeption() {
    }

    public NoSuchElementExeption(String message) {
        super(message);
    }
}
