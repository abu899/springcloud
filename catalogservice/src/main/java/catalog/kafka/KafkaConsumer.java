package catalog.kafka;

import catalog.domain.CatalogEntity;
import catalog.domain.CatalogRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {

    private final CatalogRepository catalogRepository;

    @KafkaListener(topics = "example-catalog-topic")
    public void updateQuantity(String kafkaMessage) {
        log.info("kafka message : {} ", kafkaMessage);

        Map<Object, Object> map = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            map = objectMapper.readValue(kafkaMessage, new TypeReference<Map<Object, Object>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        String productId = (String) map.get("productId");
        CatalogEntity catalog = catalogRepository.findByProductId(productId);
        if (null != catalog) {
            catalog.updateStock((Integer)map.get("quantity"));
            catalogRepository.save(catalog);
        }
    }
}
