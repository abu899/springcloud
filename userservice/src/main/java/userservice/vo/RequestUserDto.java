package userservice.vo;

import lombok.Data;

@Data
public class RequestUserDto {

    private String email;
    private String name;
    private String password;
}
