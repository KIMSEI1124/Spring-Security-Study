package com.devsei.userservice.ui;

import com.devsei.userservice.application.UserService;
import com.devsei.userservice.dto.UserDto;
import com.devsei.userservice.vo.Greeting;
import com.devsei.userservice.vo.RequestUser;
import com.devsei.userservice.vo.ResponseUser;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/")
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
        return "It's Working in User Service";
    }

    @GetMapping("/welcome")
    public String welcome() {
//        return env.getProperty("greeting.message");
        return greeting.getMessage();
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser user) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = mapper.map(user, UserDto.class);
        UserDto saveDto = userService.createUser(userDto);

        ResponseUser responseUser = mapper.map(saveDto, ResponseUser.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }
}
