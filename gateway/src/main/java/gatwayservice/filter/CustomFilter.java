package gatwayservice.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilter.Config> {

    public CustomFilter() {
        super(Config.class);
    }

    public static class Config {
        // Configuration 정보를 넣을 수 있음
    }

    @Override
    public GatewayFilter apply(Config config) {
        // JWT 와 같은 인증 작업이 들어갈 수 있다.
        // custom pre-filter
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("Custom Pre-Filter, request id = {}", request.getId());

            //Post-Filter
            return chain
                    .filter(exchange)
                    .then(
                            Mono.fromRunnable(() ->
                                    log.info("Custom Post-Filter, response status = {}", response.getStatusCode())
                            ));
        };
    }
}
