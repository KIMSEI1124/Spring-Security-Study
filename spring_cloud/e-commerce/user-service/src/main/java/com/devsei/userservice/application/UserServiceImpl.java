package com.devsei.userservice.application;

import com.devsei.userservice.domain.UserJpaEntity;
import com.devsei.userservice.domain.UserRepository;
import com.devsei.userservice.dto.OrderRes;
import com.devsei.userservice.dto.UserCreateReq;
import com.devsei.userservice.dto.UserCreateRes;
import com.devsei.userservice.dto.UserFindRes;
import com.devsei.userservice.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
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
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserMapper mapper;
    private final BCryptPasswordEncoder encoder;
    private final UserRepository userRepository;
    private final RestTemplate restTemplate;
    private final Environment environment;
    private final CircuitBreakerFactory circuitBreakerFactory;

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
//        String url = String.format("http://order-service/order-service/%s/orders", userId);
//        ResponseEntity<List<OrderRes>> orderListResponse = restTemplate.exchange(url, HttpMethod.GET, null,
//                new ParameterizedTypeReference<>() {
//                });
//        List<OrderRes> ordersList = orderListResponse.getBody();

        log.info("Before call orders MS");
        CircuitBreaker circuitbreaker = circuitBreakerFactory.create("circuitbreaker");
        List<OrderRes> ordersList = circuitbreaker.run(() -> getOrders(userId),
                throwable -> new ArrayList<>());
        log.info("After call orders MS");

        return UserFindRes.of(findUserEntity, ordersList);
    }

    private List<OrderRes> getOrders(String userId) {
        String url = String.format("http://order-service/order-service/%s/orders", userId);
        ResponseEntity<List<OrderRes>> orderListResponse = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {
                });
        return orderListResponse.getBody();
    }

    @Override
    public Iterable<UserJpaEntity> getUserByAll() {
        return userRepository.findAll();
    }
}
