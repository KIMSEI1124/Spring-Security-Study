package com.devsei.userservice.domain;


import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

@Data
@Builder
@AllArgsConstructor
@RedisHash(value = "auth_token", timeToLive = 86400000)
public class AuthToken {
    @Id
    private Long id;
    private String access;
}
