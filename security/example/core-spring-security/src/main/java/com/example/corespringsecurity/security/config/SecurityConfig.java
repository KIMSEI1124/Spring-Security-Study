package com.example.corespringsecurity.security.config;

import com.example.corespringsecurity.security.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    @Bean
    protected AuthenticationManager authenticationManager(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
        return auth.build();
    }

    @Bean
    protected InMemoryUserDetailsManager userDetailsManager() {
        String password = passwordEncoder().encode("1111");
        UserDetails user = User.builder()
                .username("user")
                .password(password)
                .roles("USER")
                .build();
        UserDetails manager = User.builder()
                .username("manager")
                .password(password)
                .roles("MANAGER", "USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password(password)
                .roles("ADMIN", "USER", "MANAGER")
                .build();
        return new InMemoryUserDetailsManager(user, manager, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .requestMatchers("/").permitAll()
                .requestMatchers("/mypage").hasRole("USER") // ( 1 )
                .requestMatchers("/messages").hasRole("MANAGER")    // ( 2 )
                .requestMatchers("/config").hasRole("ADMIN")    // ( 3 )
                .anyRequest().authenticated()

                .and()
                .formLogin();

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
}
