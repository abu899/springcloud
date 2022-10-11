package userservice.service;

import userservice.domain.UserEntity;
import userservice.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto getUserByUserId(String userId);

    List<UserEntity> getUsers();
}
