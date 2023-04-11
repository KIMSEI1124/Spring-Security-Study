package com.study.controller_advice.member.exception;

import com.study.controller_advice.exception.exception.ErrorCode;
import lombok.Getter;

@Getter
public enum MemberErrorCode implements ErrorCode {
    MEMBER_NOT_EXIST(400, "MEMBER_01", "멤버가 존재하지 않습니다.");

    private final int status;
    private final String code;
    private final String message;

    MemberErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
