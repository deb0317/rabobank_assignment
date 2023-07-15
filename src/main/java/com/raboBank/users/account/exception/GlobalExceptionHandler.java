package com.raboBank.users.account.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Exception handler for catching and handling exceptions globally.
     *
     * @param e Exception object to handle
     * @return ResponseEntity with a list of error messages
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<List<String>> handleException(Exception e) {
        return ResponseEntity.badRequest().body(List.of(e.getMessage()));
    }
}
