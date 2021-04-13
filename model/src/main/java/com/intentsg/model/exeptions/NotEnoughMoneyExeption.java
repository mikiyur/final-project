package com.intentsg.model.exeptions;

public class NotEnoughMoneyExeption extends RuntimeException{

    public NotEnoughMoneyExeption() {
    }

    public NotEnoughMoneyExeption(String message) {
        super(message);
    }
}
