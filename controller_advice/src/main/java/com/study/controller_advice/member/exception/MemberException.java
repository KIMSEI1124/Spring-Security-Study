package com.study.controller_advice.member.exception;

import com.study.controller_advice.exception.exception.ErrorCode;
import com.study.controller_advice.exception.exception.ExampleException;

public class MemberException extends ExampleException {
    public MemberException(ErrorCode code) {
        super(code);
    }
}
