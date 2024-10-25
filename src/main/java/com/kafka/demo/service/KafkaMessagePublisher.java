package com.kafka.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

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
}