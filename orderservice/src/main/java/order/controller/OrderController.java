package order.controller;

import lombok.RequiredArgsConstructor;
import order.domain.OrderEntity;
import order.dto.OrderDto;
import order.dto.RequestOrderDto;
import order.dto.ResponseOrderDto;
import order.kafka.KafkaProducer;
import order.kafka.OrderProducer;
import order.service.OrderService;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order-service")
public class OrderController {

    private final Environment env;
    private final OrderService orderService;
    private final KafkaProducer kafkaProducer;
    private final OrderProducer orderProducer;

    @GetMapping("/health")
    public String status() {
        String port = env.getProperty("local.server.port");
        return "Order controller health check : " + port;
    }

    @PostMapping("/{userId}/orders")
    public ResponseEntity<ResponseOrderDto> createOrder(
            @PathVariable("userId")String userId,
            @RequestBody RequestOrderDto requestOrder) {

        OrderDto orderDto = OrderDto.builder()
                .productId(requestOrder.getProductId())
                .quantity(requestOrder.getQuantity())
                .unitPrice(requestOrder.getUnitPrice())
                .userId(userId)
                .build();
//        OrderDto order = orderService.createOrder(orderDto);

        // kafka
        orderDto.setOrderId(UUID.randomUUID().toString());
        orderDto.setTotalPrice(orderDto.getUnitPrice() * orderDto.getQuantity());

        // Send order to kafka
        orderProducer.send("orders", orderDto);
        kafkaProducer.send("example-catalog-topic", orderDto);

        ResponseOrderDto response = ResponseOrderDto.builder()
                .orderId(orderDto.getOrderId())
                .unitPrice(orderDto.getUnitPrice())
                .productId(orderDto.getProductId())
                .totalPrice(orderDto.getTotalPrice())
                .quantity(orderDto.getQuantity())
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<ResponseOrderDto>> getOrder(@PathVariable("userId") String userId) {
        List<OrderEntity> orders = orderService.getOrdersByUserId(userId);
        List<ResponseOrderDto> responses = new ArrayList<>();
        orders.forEach(
                order -> {
                    ResponseOrderDto responseUserDto = ResponseOrderDto.builder()
                            .orderId(order.getOrderId())
                            .unitPrice(order.getUnitPrice())
                            .productId(order.getProductId())
                            .totalPrice(order.getTotalPrice())
                            .quantity(order.getQuantity())
                            .build();
                    responses.add(responseUserDto);
                }
        );

        return ResponseEntity.ok(responses);
    }
}
