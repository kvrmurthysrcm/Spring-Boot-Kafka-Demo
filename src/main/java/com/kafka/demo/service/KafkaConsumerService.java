package com.kafka.demo.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "kvrm-topic-1", groupId = "kvrm-group")
    public void consume1(String message) {
        System.out.println("1. Consumed message: " + message);
    }

    @KafkaListener(topics = "kvrm-topic-1", groupId = "kvrm-group")
    public void consume2(String message) {
        System.out.println("2. Consumed message: " + message);
    }

    @KafkaListener(topics = "kvrm-topic-1", groupId = "kvrm-group")
    public void consume3(String message) {
        System.out.println("3. Consumed message: " + message);
    }
}