package order.kafka;

import lombok.AllArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
public class KafkaOrderDto implements Serializable {
    private Schema schema;
    private Payload payload;
}
