package com.devsei.userservice.security;

import com.devsei.userservice.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.util.matcher.IpAddressMatcher;

import java.util.function.Supplier;

@RequiredArgsConstructor
@Configuration
@Slf4j
public class SecurityConfig {
    private static final String ALLOWED_IP_ADDRESS = "192.0.0.2"; // API Gateway
    private static final IpAddressMatcher ALLOWED_ID_ADDRESS_MATCHER = new IpAddressMatcher(ALLOWED_IP_ADDRESS);
    private final UserRepository userRepository;
    private final Environment environment;
    private final AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagementConfigurer ->
                        sessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(registry ->
                        registry.requestMatchers("/**").access(this::hasIpAddress))
                .addFilter(getAuthenticationFilter())
                .build();
    }

    // [hasIpAddress 사용하기 6.2 버젼](https://jongmin4943.tistory.com/entry/Spring-Security-6-hasIpAddress-%EC%82%AC%EC%9A%A9%ED%95%98%EB%8A%94%EB%B2%95)
    private AuthorizationDecision hasIpAddress(Supplier<Authentication> authentication,
                                               RequestAuthorizationContext object) {
        return new AuthorizationDecision(ALLOWED_ID_ADDRESS_MATCHER.matches(object.getRequest())
        );
    }

    private AuthenticationFilter getAuthenticationFilter() throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(userRepository, environment);
        authenticationFilter.setAuthenticationManager(authenticationManager(authenticationConfiguration));
        return authenticationFilter;
    }

    // https://www.inflearn.com/questions/794221/ajax-%EC%9D%B8%EC%A6%9D%EC%8B%9C-authenticationmanager-%EB%93%B1%EB%A1%9D-%EB%AC%B8%EC%9D%98
    // https://covenant.tistory.com/277 - 자동으로 UserDetailsService, passwordEncoder 설정
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
