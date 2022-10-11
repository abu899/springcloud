package userservice.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ResponseOrderDto {

    private String productId;
    private Integer quantity;
    private Integer unitPrice;
    private Integer totalPrice;
    private LocalDate createdAt;
    private String orderId;
}
