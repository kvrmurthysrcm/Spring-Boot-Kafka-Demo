package com.kafka.demo.service;

import com.kafka.demo.config.KafkaProducerConfig;
import com.kafka.demo.dto.Product;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

import static com.kafka.demo.config.KafkaConfig.KVRM_GROUP;
import static com.kafka.demo.config.KafkaConfig.PRODUCT_TOPIC;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = PRODUCT_TOPIC, groupId = KVRM_GROUP,
        topicPartitions = {@TopicPartition(topic=PRODUCT_TOPIC, partitions={"0"})})
    public void consume1(String product) {
        System.out.println("1. Consumed on PARTITION 0:: Product: " + product);
    }

    @KafkaListener(topics = PRODUCT_TOPIC, groupId = KVRM_GROUP,
            topicPartitions = {@TopicPartition(topic=PRODUCT_TOPIC, partitions={"1"})})
    public void consume2(String product) {
        System.out.println("2. Consumed on PARTITION 1:: Product: " + product);
    }

    @KafkaListener(topics = PRODUCT_TOPIC, groupId = KVRM_GROUP,
            topicPartitions = {@TopicPartition(topic=PRODUCT_TOPIC, partitions={"2"})})
    public void consume3(String product) {
        System.out.println("3. Consumed on PARTITION 2:: Product: " + product);
    }

    @KafkaListener(topics = PRODUCT_TOPIC, groupId = KVRM_GROUP,
    topicPartitions = {@TopicPartition(topic=PRODUCT_TOPIC, partitions={"3"})})
    public void consume4(String product) {
        System.out.println("4. Consumed on PARTITION 3:: Product: " + product);
    }

    @KafkaListener(topics = PRODUCT_TOPIC, groupId = KVRM_GROUP,
            topicPartitions = {@TopicPartition(topic=PRODUCT_TOPIC, partitions={"4"})})
    public void consume5(String product) {
        System.out.println("5. Consumed on PARTITION 4:: Product: " + product);
    }

//      issues with recognising the JsonDeserializer..
//    // TODO: Need to figure out why this is not working:
//    @KafkaListener(topics = PRODUCT_TOPIC, groupId = KVRM_GROUP,
//            topicPartitions = {@TopicPartition(topic=PRODUCT_TOPIC, partitions={"4"})})
//    public void consume6(Product product) {
//        System.out.println("5. Consumed Product: " + product);
//    }

}