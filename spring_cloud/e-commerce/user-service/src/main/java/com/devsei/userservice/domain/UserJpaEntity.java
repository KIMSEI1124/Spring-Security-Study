package com.devsei.userservice.domain;

import jakarta.persistence.*;
import lombok.Data;

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
}