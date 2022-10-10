package userservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseUserDto {
    private String email;
    private String name;
    private String userId;
}
