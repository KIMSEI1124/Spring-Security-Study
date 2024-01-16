package com.devsei.userservice.application;

import com.devsei.userservice.domain.UserJpaEntity;
import com.devsei.userservice.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserJpaEntity userJpaEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Username is Not Existed"));
        log.info("loadUserByUsername() ---> [{}]", userJpaEntity);

        // OAuth2 로 수정필요
        SimpleGrantedAuthority simpleGrantedAuthority =
                new SimpleGrantedAuthority(userJpaEntity.getRole().getValue());
        return new User(userJpaEntity.getUserId(),
                userJpaEntity.getEncryptedPassword(),
                Collections.singleton(simpleGrantedAuthority));
    }
}
