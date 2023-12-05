package com.example.netology_diplom_moneytransferservice.controller.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.example.netology_diplom_moneytransferservice.exception.CardInvalidCvvException;
import com.example.netology_diplom_moneytransferservice.exception.CardInvalidDateException;
import com.example.netology_diplom_moneytransferservice.exception.CardNumberNotFoundException;
import com.example.netology_diplom_moneytransferservice.exception.NotEnoughMoneyException;

@RestControllerAdvice
public class ExceptionControllerAdvice {
    private final Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

    @ExceptionHandler(CardNumberNotFoundException.class)
    public ResponseEntity<String> cardNumberNotFoundException(CardNumberNotFoundException ex) {
        logger.info("Ошибка перевода " + ex.getMessage());
        return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
    }

    @ExceptionHandler(NotEnoughMoneyException.class)
    public ResponseEntity<String> notEnoughMoneyException(NotEnoughMoneyException ex) {
        logger.info("Ошибка перевода " + ex.getMessage());
        return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
    }

    @ExceptionHandler(CardInvalidDateException.class)
    public ResponseEntity<String> cardInvalidDateException(CardInvalidDateException ex) {
        logger.info("Ошибка перевода " + ex.getMessage());
        return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
    }

    @ExceptionHandler(CardInvalidCvvException.class)
    public ResponseEntity<String> cardNumberNotFoundException(CardInvalidCvvException ex) {
        logger.info("Ошибка перевода " + ex.getMessage());
        return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
    }
}