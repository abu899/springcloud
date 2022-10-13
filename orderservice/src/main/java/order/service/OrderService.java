package order.service;

import order.domain.OrderEntity;
import order.dto.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDto);

    OrderDto getOrderByOrderId(String orderId);

    List<OrderEntity> getOrdersByUserId(String userId);


}
