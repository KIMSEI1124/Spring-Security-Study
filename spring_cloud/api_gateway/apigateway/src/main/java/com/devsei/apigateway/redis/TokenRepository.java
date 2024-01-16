package com.devsei.apigateway.redis;

import org.springframework.data.repository.CrudRepository;

public interface TokenRepository extends CrudRepository<AuthToken, Long> {
}
