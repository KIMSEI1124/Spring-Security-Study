package com.study.controller_advice.exception.exception;

public interface ErrorCode {
    int getStatus();
    String getMessage();
    String getCode();
}