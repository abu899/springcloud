package userservice.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import userservice.domain.UserEntity;
import userservice.dto.UserDto;

import java.util.List;

public interface UserService extends UserDetailsService {

    UserDto createUser(UserDto userDto);

    UserDto getUserByUserId(String userId);

    List<UserEntity> getUsers();
}
