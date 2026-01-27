package com.lowlayer.app.exceptions;

import java.util.HashMap;
import java.util.Map;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.lowlayer.app.dto.ErrorResponseDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDTO<String>> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(new ErrorResponseDTO<>(ErrorCode.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO<Map<String, String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, BindingResult result) {

        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), err.getDefaultMessage());
        });

        ErrorResponseDTO<Map<String, String>> errorResponse = new ErrorResponseDTO<>(ErrorCode.VALIDATION_ERROR, errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(errorResponse);
    }
}
