package com.example.netology_diplom_moneytransferservice.exception;

public class NotEnoughMoneyException extends RuntimeException {
    private final String message;

    public NotEnoughMoneyException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}