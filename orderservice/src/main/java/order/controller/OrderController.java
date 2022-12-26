package order.controller;

import lombok.RequiredArgsConstructor;
import order.domain.OrderEntity;
import order.dto.OrderDto;
import order.dto.RequestOrderDto;
import order.dto.ResponseOrderDto;
import order.kafka.KafkaProducer;
import order.service.OrderService;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order-service")
public class OrderController {

    private final Environment env;
    private final OrderService orderService;
    private final KafkaProducer kafkaProducer;

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
        OrderDto order = orderService.createOrder(orderDto);

        ResponseOrderDto response = ResponseOrderDto.builder()
                .orderId(order.getOrderId())
                .unitPrice(order.getUnitPrice())
                .productId(order.getProductId())
                .totalPrice(order.getTotalPrice())
                .quantity(order.getQuantity())
                .build();

        // Send order to kafka
        kafkaProducer.send("example-catalog-topic", orderDto);

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
