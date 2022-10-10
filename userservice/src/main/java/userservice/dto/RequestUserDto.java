package userservice.dto;

import lombok.Data;

@Data
public class RequestUserDto {

    private String email;
    private String name;
    private String password;
}
