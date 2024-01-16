package com.devsei.userservice.application;

import com.devsei.userservice.domain.AuthToken;
import com.devsei.userservice.domain.TokenRepository;
import com.devsei.userservice.domain.UserJpaEntity;
import com.devsei.userservice.domain.UserRepository;
import com.devsei.userservice.dto.LoginReq;
import com.devsei.userservice.dto.LoginRes;
import com.devsei.userservice.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
@Slf4j
public class AuthService {
    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;

    public LoginRes login(LoginReq req) {
        UserJpaEntity userJpaEntity = userRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Email is not existed"));
        log.info("login() -----> hello");
        Authentication authenticate = authenticationManagerBuilder.getObject()
                .authenticate(req.toAuthentication());
        LoginRes loginRes = tokenProvider.generateTokenResponse(authenticate);
        tokenRepository.save(AuthToken.builder()
                .id(userJpaEntity.getId())
                .access(loginRes.getRefreshToken())
                .build());
        return loginRes;
    }
}
