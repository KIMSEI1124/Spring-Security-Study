package com.multi.mybatis;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class MemberResponse {
    private String id;
    private String pw;

    @Builder
    public MemberResponse(String id, String pw) {
        this.id = id;
        this.pw = pw;
    }
}
