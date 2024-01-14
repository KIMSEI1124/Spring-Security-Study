package com.devsei.userservice.ui;

import com.devsei.userservice.vo.Greeting;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class UserController {

    private final Greeting greeting;
//    private final Environment env;

//    @Autowired
//    public UserController(Environment env) {
//        this.env = env;
//    }

    @GetMapping("/health_check")
    public String status() {
        return "It's Working in User Service";
    }

    @GetMapping("/welcome")
    public String welcome() {
//        return env.getProperty("greeting.message");
        return greeting.getMessage();
    }
}
