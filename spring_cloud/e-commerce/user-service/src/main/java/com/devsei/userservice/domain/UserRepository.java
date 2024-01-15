package com.devsei.userservice.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserJpaEntity, Long> {
    Optional<UserJpaEntity> findByUserId(String userId);

    Optional<UserJpaEntity> findByEmail(String email);
}
