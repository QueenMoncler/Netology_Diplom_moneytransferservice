package com.example.netology_diplom_moneytransferservice.exception;

public class CardInvalidCvvException extends RuntimeException {
    private final String message;

    public CardInvalidCvvException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}