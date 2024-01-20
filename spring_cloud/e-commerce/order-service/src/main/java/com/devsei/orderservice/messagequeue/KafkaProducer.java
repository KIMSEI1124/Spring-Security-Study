package com.devsei.orderservice.messagequeue;

import com.devsei.orderservice.vo.OrderVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
@Slf4j
public class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public OrderVo send(String topic, OrderVo orderVo) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonIsString = "";
        try {
            jsonIsString = mapper.writeValueAsString(orderVo);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        kafkaTemplate.send(topic, jsonIsString);
        log.info("Kafka Producer sent data from the Order microservice: [{}]", orderVo);
        return orderVo;
    }
}
