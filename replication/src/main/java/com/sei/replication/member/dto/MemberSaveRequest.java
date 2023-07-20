package com.sei.replication.member.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access =  AccessLevel.PROTECTED)
@Data
public class MemberSaveRequest {
    private String name;
    private int age;

    @Builder
    public MemberSaveRequest(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
