package com.intentsg.service.tour.exeption;

public class NoSuchElementExeption extends RuntimeException {

    public NoSuchElementExeption() {
    }

    public NoSuchElementExeption(String message) {
        super(message);
    }
}
