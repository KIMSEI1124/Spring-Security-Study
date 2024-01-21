package com.devsei.catalogservice.messagequeue;

import com.devsei.catalogservice.domain.CatalogJpaEntity;
import com.devsei.catalogservice.domain.CatalogRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Slf4j
public class KafkaConsumer {
    private final CatalogRepository repository;

    @Transactional
    @KafkaListener(topics = "example-catalog-topic")
    public void updateQuantity(String kafkaMessage) {
        log.info("Kafka Message : -> [{}]", kafkaMessage);

        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            map = mapper.readValue(kafkaMessage, new TypeReference<Map<Object, Object>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        CatalogJpaEntity catalogJpaEntity = repository.findByProductId((String) map.get("productId"));

        if (catalogJpaEntity != null) {
            catalogJpaEntity.setStock(catalogJpaEntity.getStock() - (Integer) map.get("quantity"));
        }

        log.info("Method End Time : [{}]", LocalDateTime.now());
    }
}
