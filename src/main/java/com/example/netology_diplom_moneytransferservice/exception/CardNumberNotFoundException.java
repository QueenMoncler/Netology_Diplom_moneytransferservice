package com.example.netology_diplom_moneytransferservice.exception;

public class CardNumberNotFoundException extends RuntimeException {
    private final String message;

    public CardNumberNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}