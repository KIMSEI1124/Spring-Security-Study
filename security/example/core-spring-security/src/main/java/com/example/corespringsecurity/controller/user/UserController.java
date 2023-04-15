package com.example.corespringsecurity.controller.user;

import com.example.corespringsecurity.domain.AccountDto;
import com.example.corespringsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public String createUser() {
        return "user/login/register";
    }

    @PostMapping("/users")
    public String createUser(AccountDto dto) {
        return "/home";
    }
}
