package com.kafka.demo.service;

import com.kafka.demo.config.KafkaProducerConfig;
import com.kafka.demo.dto.Product;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static com.kafka.demo.config.KafkaConfig.KVRM_GROUP;
import static com.kafka.demo.config.KafkaConfig.PRODUCT_TOPIC;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = PRODUCT_TOPIC, groupId = KVRM_GROUP)
    public void consume1(String product) {
        System.out.println("1. Consumed Product: " + product);
    }
//
//    @KafkaListener(topics = PRODUCT_TOPIC, groupId = KVRM_GROUP)
//    public void consume2(Product product) {
//        System.out.println("2. Consumed Product: " + product.toString());
//    }

//    @KafkaListener(topics = PRODUCT_TOPIC, groupId = KVRM_GROUP)
//    public void consume3(Product product) {
//        System.out.println("3. Consumed Product: " + product.toString());
//    }
}