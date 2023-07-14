package com.raboBank.users.account.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<List<String>> handleException(Exception e) {
        return ResponseEntity.badRequest().body(List.of(e.getMessage()));
    }
}

