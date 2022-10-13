package order.dto;

import lombok.Data;

@Data
public class RequestOrderDto {
    private Integer quantity;
    private Integer unitPrice;
    private String productId;
}
