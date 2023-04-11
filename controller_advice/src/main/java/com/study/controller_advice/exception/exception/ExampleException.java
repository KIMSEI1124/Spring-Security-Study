package com.study.controller_advice.exception.exception;

import lombok.Getter;

@Getter
public class ExampleException extends RuntimeException {
    private final int statusCode;
    private final String errorCode;
    private final String message;

    public ExampleException(ErrorCode code) {
        this.statusCode = code.getStatus();
        this.errorCode = code.getCode();
        this.message = code.getMessage();
    }
}
