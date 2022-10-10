package userservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import userservice.domain.UserEntity;
import userservice.domain.UserRepository;
import userservice.dto.UserDto;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());
        userDto.setEncryptedPassword("encrypted_password");

        UserEntity userEntity = UserEntity.createUserEntity(userDto);
        UserEntity savedUserEntity = userRepository.save(userEntity);

        return null;
    }
}
