package com.study.controller_advice.exception;

import com.study.controller_advice.exception.dto.response.ExceptionResponse;
import com.study.controller_advice.exception.exception.ExampleException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(ExampleException.class)
    public ResponseEntity<ExceptionResponse> handleException(ExampleException e) {
        int statusCode = e.getStatusCode();
        ExceptionResponse response = ExceptionResponse.from(e.getMessage());

        return ResponseEntity.status(statusCode).body(response);
    }
}
