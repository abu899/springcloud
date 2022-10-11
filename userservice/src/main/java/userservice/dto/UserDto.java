package userservice.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class UserDto {

    private String email;
    private String name;
    private String password;
    private String userId;
    private LocalDate createdAt;
    private String encryptedPassword;

    private List<ResponseOrderDto> orders;
}
