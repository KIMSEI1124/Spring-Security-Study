package com.study.redis;

import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "member", timeToLive = 60)
public class Member {
    @Id
    private String id;
    private String name;
    private Integer age;
    private LocalDate date;

    @Builder
    public Member(String name, Integer age) {
        this.name = name;
        this.age = age;
        this.date = LocalDate.now();
    }
}
