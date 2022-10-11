package userservice.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResponseUserDto {
    private String email;
    private String name;
    private String userId;

    private List<ResponseOrderDto> orders;
}
