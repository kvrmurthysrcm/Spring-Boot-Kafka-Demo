package com.kafka.demo.service;

import com.kafka.demo.config.KafkaProducerConfig;
import com.kafka.demo.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

import static com.kafka.demo.config.KafkaConfig.PRODUCT_TOPIC;

@Service
public class KafkaMessagePublisher {

    @Autowired
    KafkaTemplate<String, Object> template;

    public void sendMessageToTopic(String message){
        System.out.println("@Service:: @sendMessageToTopic(" + message + ")");
        for(int i = 0; i < 10000; i++) {
            // publish the message:
            CompletableFuture<SendResult<String, Object>> future = template.send("kvrm-topic-1", message + "-" + i);
            // Exception/logs processing
            future.whenComplete((result, ex) ->{
                if(ex == null) {
                    System.out.println("Send message=[" + message +
                            "] with offset=[" + result.getRecordMetadata().offset() + "]");
                } else {
                    System.out.println("Unable to send message=[" + message +
                            "] due to=" + ex.getMessage() );
                }
            });
        }
    }

    public void sendProductToTopic(Product product) {
        System.out.println("@Service:: @sendProductToTopic(" + product.toString() + ")");
        //for(int i = 0; i < 10000; i++) {
            // publish the message:
            CompletableFuture<SendResult<String, Object>> future = template.send(PRODUCT_TOPIC, product );
            // Exception/logs processing
            future.whenComplete((result, ex) ->{
                if(ex == null) {
                    System.out.println("Send message=[" + product.toString() +
                            "] with offset=[" + result.getRecordMetadata().offset() + "]");
                } else {
                    System.out.println("Unable to send message=[" + product.toString() +
                            "] due to=" + ex.getMessage() );
                }
            });
       // }

    }
}