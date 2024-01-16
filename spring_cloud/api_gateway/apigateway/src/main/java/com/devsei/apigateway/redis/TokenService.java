package com.devsei.apigateway.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class TokenService {
    private final TokenRepository tokenRepository;

    public AuthToken findById(Long id) {
        return tokenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("로그인 정보가 없습니다."));
    }
}
