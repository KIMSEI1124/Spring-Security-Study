package io.security.basicSecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public static BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/user").hasRole("USER")
                .antMatchers("/admin/pay").hasRole("ADMIN")
                .antMatchers("/admin/**").access("hasRole('ADMIN') or hasRole('SYS')")
                .anyRequest().authenticated();

        http
                .formLogin()
//                .loginPage("/loginPage").permitAll()
                .defaultSuccessUrl("/")
                .failureUrl("/login")
                .usernameParameter("userId")
                .passwordParameter("passwd")
                .loginProcessingUrl("/login_proc")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request
                            , HttpServletResponse response
                            , Authentication authentication) throws IOException, ServletException {
                        System.out.println("authentication = " + authentication.getName());
                        response.sendRedirect("/");
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request
                            , HttpServletResponse response
                            , AuthenticationException exception) throws IOException, ServletException {
                        System.out.println("exception = " + exception.getMessage());
                        response.sendRedirect("/login");
                    }
                });

        http
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .addLogoutHandler(
                        new LogoutHandler() {
                            @Override
                            public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
                                HttpSession session = request.getSession();
                                session.invalidate();   // 세션 무효화 작업,
                                // 여기서 하지 않으면 SecurityContextLogoutHandler.java 에서 세션 무효화 작업을 진행한다.
                            }
                        }
                )
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        response.sendRedirect("/login");    // 로그아웃 성공후 이동할 페이지
                    }
                })
                .deleteCookies("remember-me");  // 쿠키 삭제

        http
                .rememberMe()
                .rememberMeParameter("remember")
                .tokenValiditySeconds(3600) // 1시간
                .userDetailsService(userDetailsService(bCryptPasswordEncoder()));

        http
                .sessionManagement()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true);

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(BCryptPasswordEncoder bCryptPasswordEncoder) {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("kim")
                .password(bCryptPasswordEncoder.encode("1234"))
                .roles("USER")
                .build());

        manager.createUser(User.withUsername("sys")
                .password(bCryptPasswordEncoder.encode("1234"))
                .roles("SYS")
                .build());

        manager.createUser(User.withUsername("admin")
                .password(bCryptPasswordEncoder.encode("1234"))
                .roles("ADMIN")
                .build());

        return manager;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, UserDetailsService userDetailsService,
                                                       BCryptPasswordEncoder bCryptPasswordEncoder) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder)
                .and().build();
    }
}

