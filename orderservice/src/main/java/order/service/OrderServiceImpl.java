package order.service;

import lombok.RequiredArgsConstructor;
import order.domain.OrderEntity;
import order.domain.OrderRepository;
import order.dto.OrderDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        orderDto.setOrderId(UUID.randomUUID().toString());
        orderDto.setTotalPrice(orderDto.getUnitPrice() * orderDto.getQuantity());

        OrderEntity order = OrderEntity.createOrderEntity(orderDto);
        orderRepository.save(order);

        return orderDto;
    }

    @Override
    public OrderDto getOrderByOrderId(String orderId) {
        OrderEntity order = orderRepository.findByOrderId(orderId);
        return OrderDto.builder()
                .orderId(orderId)
                .productId(order.getProductId())
                .quantity(order.getQuantity())
                .totalPrice(order.getTotalPrice())
                .unitPrice(order.getUnitPrice())
                .userId(order.getUserId())
                .build();
    }

    @Override
    public List<OrderEntity> getOrdersByUserId(String userId) {
        return orderRepository.findByUserId(userId);
    }
}
