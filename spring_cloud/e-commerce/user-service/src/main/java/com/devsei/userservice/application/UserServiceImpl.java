package com.devsei.userservice.application;

import com.devsei.userservice.domain.UserJpaEntity;
import com.devsei.userservice.domain.UserRepository;
import com.devsei.userservice.dto.OrderRes;
import com.devsei.userservice.dto.UserCreateReq;
import com.devsei.userservice.dto.UserCreateRes;
import com.devsei.userservice.dto.UserFindRes;
import com.devsei.userservice.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserMapper mapper;
    private final BCryptPasswordEncoder encoder;
    private final UserRepository userRepository;
    private final RestTemplate restTemplate;
    private final Environment environment;

    @Override
    public UserCreateRes createUser(UserCreateReq req) {
        UserJpaEntity savedEntity = userRepository.save(mapper.toJpaEntity(req, encoder));

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

        /* Using RestTemplate */
        String url = String.format("http://order-service/order-service/%s/orders", userId);
        ResponseEntity<List<OrderRes>> orderListResponse = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {
                });
        List<OrderRes> ordersList = orderListResponse.getBody();

        return UserFindRes.of(findUserEntity, ordersList);
    }

    @Override
    public Iterable<UserJpaEntity> getUserByAll() {
        return userRepository.findAll();
    }
}
