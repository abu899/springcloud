package userservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import userservice.vo.Greeting;
import userservice.vo.RequestUserDto;

@RestController
@Slf4j
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {

    private final Environment env;
    private final Greeting greeting;

    @GetMapping("/health")
    public String status() {
        return "User controller health check";
    }

    @GetMapping("/welcome")
    public String welcome() {
//        return env.getProperty("greeting.message");
        return greeting.getGreetingMessage();
    }

    @PostMapping("/users")
    public String createUser(@RequestBody RequestUserDto user) {
        return "Create user method is called";
    }
}
