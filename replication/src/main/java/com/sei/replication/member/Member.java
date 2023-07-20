package com.sei.replication.member;

import com.sei.replication.member.dto.MemberSaveRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;

    @Builder
    private Member(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static Member from(MemberSaveRequest request) {
        return Member.builder()
                .name(request.getName())
                .age(request.getAge())
                .build();
    }
}
