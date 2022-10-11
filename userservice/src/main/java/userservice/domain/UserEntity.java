package userservice.domain;

import lombok.*;
import userservice.dto.UserDto;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String name;
    private String userId;
    private String encryptedPassword;

    public static UserEntity createUserEntity(UserDto userDto) {
        return UserEntity.builder()
                .email(userDto.getEmail())
                .name(userDto.getName())
                .userId(userDto.getUserId())
                .build();
    }
}
