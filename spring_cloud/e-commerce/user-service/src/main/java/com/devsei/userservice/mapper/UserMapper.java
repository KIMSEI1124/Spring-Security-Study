package com.devsei.userservice.mapper;

import com.devsei.userservice.domain.UserJpaEntity;
import com.devsei.userservice.dto.UserCreateReq;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class UserMapper {
    public UserJpaEntity toJpaEntity(UserCreateReq req, BCryptPasswordEncoder encoder) {
        return UserJpaEntity.builder()
                .email(req.email())
                .name(req.name())
                .userId(UUID.randomUUID().toString())
                .encryptedPassword(encoder.encode(req.password()))
                .build();
    }
}