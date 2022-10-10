package userservice.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserDto {

    private String email;
    private String name;
    private String password;
    private String userId;
    private LocalDate createdAt;
    private String encryptedPassword;
}
