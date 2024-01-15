package com.devsei.userservice.application;

import com.devsei.userservice.domain.UserJpaEntity;
import com.devsei.userservice.domain.UserRepository;
import com.devsei.userservice.mapper.UserMapper;
import com.devsei.userservice.vo.OrderRes;
import com.devsei.userservice.vo.UserCreateReq;
import com.devsei.userservice.vo.UserCreateRes;
import com.devsei.userservice.vo.UserFindRes;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserMapper mapper;

    private final UserRepository userRepository;

    @Override
    public UserCreateRes createUser(UserCreateReq req) {
        UserJpaEntity savedEntity = userRepository.save(mapper.toJpaEntity(req));

        return UserCreateRes.builder()
                .userId(savedEntity.getUserId())
                .email(savedEntity.getEmail())
                .name(savedEntity.getName())
                .build();
    }

    @Override
    public UserFindRes getUserByUserId(String userId) {
        UserJpaEntity findUserEntity = userRepository.findByUserId(userId).orElseThrow(
                () -> new UsernameNotFoundException("User not Found")
        );

        return UserFindRes.of(findUserEntity, new ArrayList<>());
    }

    @Override
    public Iterable<UserJpaEntity> getUserByAll() {
        return userRepository.findAll();
    }
}
