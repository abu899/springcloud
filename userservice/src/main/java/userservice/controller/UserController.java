package userservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import userservice.dto.ResponseUserDto;
import userservice.dto.UserDto;
import userservice.service.UserService;
import userservice.vo.Greeting;
import userservice.dto.RequestUserDto;

@RestController
@Slf4j
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {

    private final Environment env;
    private final Greeting greeting;
    private final UserService userService;

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
    public ResponseEntity<ResponseUserDto> createUser(@RequestBody RequestUserDto user) {
        UserDto userDto = UserDto.builder()
                .email(user.getEmail())
                .name(user.getName())
                .password(user.getPassword())
                .build();
        userService.createUser(userDto);

        ResponseUserDto response = ResponseUserDto.builder()
                .userId(userDto.getUserId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
        return ResponseEntity.ok(response);
    }
}
