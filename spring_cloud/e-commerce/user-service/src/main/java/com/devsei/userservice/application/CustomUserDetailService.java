package com.devsei.userservice.application;

import com.devsei.userservice.domain.UserJpaEntity;
import com.devsei.userservice.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserJpaEntity userJpaEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Username is Not Existed"));
        // OAuth2 로 수정필요
        return new User(userJpaEntity.getUserId(), userJpaEntity.getEncryptedPassword(), new ArrayList<>());
    }
}
