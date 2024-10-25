package com.kafka.demo.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaProducerConfig {

    @Bean
    public NewTopic createTopic(){
        System.out.println("Creating the topic: kvrm-topic-1");
        return new NewTopic("kvrm-topic-1", 5, (short)1);
    }
}
