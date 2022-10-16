package userservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import userservice.domain.UserEntity;
import userservice.dto.RequestUserDto;
import userservice.dto.ResponseUserDto;
import userservice.dto.UserDto;
import userservice.service.UserService;
import userservice.vo.Greeting;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
//@RequestMapping("/user-service")
@RequiredArgsConstructor
public class UserController {

    private final Environment env;
    private final Greeting greeting;
    private final UserService userService;

    @GetMapping("/health")
    public String status() {
        String port = env.getProperty("local.server.port");
        return "User controller health check : " + port;
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

    @GetMapping("/users")
    public ResponseEntity<List<ResponseUserDto>> getUsers() {
        List<UserEntity> users = userService.getUsers();
        List<ResponseUserDto> responses = new ArrayList<>();
        users.forEach(
                user -> {
                    ResponseUserDto responseUserDto = ResponseUserDto.builder()
                            .email(user.getEmail())
                            .name(user.getName())
                            .userId(user.getUserId())
                            .orders(new ArrayList<>())
                            .build();
                    responses.add(responseUserDto);
                }
        );

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<ResponseUserDto> getUser(@PathVariable("userId") String userId) {
        UserDto userDto = userService.getUserByUserId(userId);
        ResponseUserDto response = ResponseUserDto.builder()
                .email(userDto.getEmail())
                .userId(userDto.getUserId())
                .name(userDto.getName())
                .orders(userDto.getOrders())
                .build();

        return ResponseEntity.ok(response);
    }
}
