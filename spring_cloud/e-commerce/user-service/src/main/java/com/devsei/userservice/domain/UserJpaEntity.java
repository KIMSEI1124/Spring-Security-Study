package com.devsei.userservice.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Table(name = "users")
@Entity
public class UserJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String name;
    private String userId;
    private String encryptedPassword;

    @Builder
    public UserJpaEntity(String email, String name, String userId, String encryptedPassword) {
        this.email = email;
        this.name = name;
        this.userId = userId;
        this.encryptedPassword = encryptedPassword;
    }
}