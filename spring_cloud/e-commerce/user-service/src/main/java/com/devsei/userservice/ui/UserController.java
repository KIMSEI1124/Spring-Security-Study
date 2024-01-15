package com.devsei.userservice.ui;

import com.devsei.userservice.application.UserService;
import com.devsei.userservice.domain.UserJpaEntity;
import com.devsei.userservice.vo.Greeting;
import com.devsei.userservice.vo.UserCreateReq;
import com.devsei.userservice.vo.UserCreateRes;
import com.devsei.userservice.vo.UserFindRes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user-service")
public class UserController {

    private final UserService userService;

    private final Greeting greeting;
//    private final Environment env;

//    @Autowired
//    public UserController(Environment env) {
//        this.env = env;
//    }

    @GetMapping("/health_check")
    public String status() {
        return "It's Working in User Service on PORT";
    }

    @GetMapping("/welcome")
    public String welcome() {
//        return env.getProperty("greeting.message");
        return greeting.getMessage();
    }

    @PostMapping("/users")
    public ResponseEntity<UserCreateRes> createUser(@RequestBody UserCreateReq req) {
        UserCreateRes res = userService.createUser(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserFindRes>> getUsers() {
        List<UserFindRes> res = new ArrayList<>();
        for (UserJpaEntity userJpaEntity : userService.getUserByAll())
            res.add(UserFindRes.builder()
                    .name(userJpaEntity.getName())
                    .email(userJpaEntity.getEmail())
                    .userId(userJpaEntity.getUserId())
                    .orders(new ArrayList<>())
                    .build());

        return ResponseEntity.ok(res);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserFindRes> getUser(@PathVariable("userId") String userId) {
        UserFindRes res = userService.getUserByUserId(userId);
        return ResponseEntity.ok(res);
    }
}
