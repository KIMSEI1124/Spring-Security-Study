package com.querydsl.group.member;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class MemberDto {

    private String memberName;
    private String teamName;

    @QueryProjection
    public MemberDto(String memberName, String teamName) {
        this.memberName = memberName;
        this.teamName = teamName;
    }
}
