package com.kafka.demo.controller;

import com.kafka.demo.dto.Product;
import com.kafka.demo.service.KafkaMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
public class HomeController {

    @Autowired
    KafkaMessagePublisher kafkaMessagePublisher;

    // http://localhost:9080/kafka/publish/helloworldmessagetobesent
    @RequestMapping("/publish/{message}")
    public ResponseEntity<?> sendMessageToTopic(@PathVariable String message){
        System.out.println("@Controller:: @sendMessageToTopic(" + message + ")");
        try {
            kafkaMessagePublisher.sendMessageToTopic(message);
            return ResponseEntity.ok("Message published successfully!");
        } catch(Exception ex){
            System.out.println(" Exception occcured while Publishing the message: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // http://localhost:9080/kafka/publish-product
    @PostMapping("/publish-product")
    public ResponseEntity<?> sendProductToTopic(@RequestBody Product product){
        System.out.println("@Controller:: @sendProductToTopic(" + product.toString() + ")");
        try {
            kafkaMessagePublisher.sendProductToTopic(product);
            return ResponseEntity.ok("Message published successfully!");
        } catch(Exception ex){
            System.out.println(" Exception occcured while Publishing the message: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    // http://localhost:9080/kafka/partition/publish-product
    @PostMapping("/partition/publish-product")
    public ResponseEntity<?> sendProductToTopicPartition(@RequestBody Product product){
        System.out.println("@Controller:: @sendProductToTopicPartition(" + product.toString() + ")");
        try {
            kafkaMessagePublisher.sendProductToTopicPartition(product);
            return ResponseEntity.ok("Message published successfully!");
        } catch(Exception ex){
            System.out.println(" Exception occcured while Publishing the message: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}