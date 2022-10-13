package order.domain;

import lombok.*;
import order.dto.OrderDto;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class OrderEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productId;
    private Integer quantity;
    private Integer unitPrice;
    private Integer totalPrice;
    private String userId;
    private String orderId;
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    public static OrderEntity createOrderEntity(OrderDto userDto) {
        return OrderEntity.builder()
                .productId(userDto.getProductId())
                .quantity(userDto.getQuantity())
                .unitPrice(userDto.getUnitPrice())
                .totalPrice(userDto.getTotalPrice())
                .userId(userDto.getUserId())
                .orderId(userDto.getOrderId())
                .build();
    }
}
