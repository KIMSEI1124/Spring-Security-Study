package com.example.corespringsecurity.service;

import com.example.corespringsecurity.domain.Account;
import com.example.corespringsecurity.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void createUser(Account account) {
        userRepository.save(account);
    }
}
