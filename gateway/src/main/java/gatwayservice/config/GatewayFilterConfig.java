package gatwayservice.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class GatewayFilterConfig {


    /**
     * application.yml 내 내용을 java code 로
     * cloud:
     * gateway:
     * routes:
     * - id: first-service
     * uri: http://localhost:8081
     * predicates:
     * - Path=/first-service/**
     * - id: second-service
     * uri: http://localhost:8082
     * predicates:
     * - Path=/second-service/**
     */
//    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder locatorBuilder) {
        return locatorBuilder.routes()
                .route(r -> r
                        .path("/first-service/**")
                        .filters(f -> f
                                .addRequestHeader("first-request", "first-request-value")
                                .addResponseHeader("first-response", "first-response-value"))
                        .uri("http://localhost:8081"))
                .route(r -> r
                        .path("/second-service/**")
                        .filters(f -> f
                                .addRequestHeader("second-request", "second-request-value")
                                .addResponseHeader("second-response", "second-response-value"))
                        .uri("http://localhost:8082"))
                .build();
    }
}
