package com.kafka.demo.config;

import com.kafka.demo.dto.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

import static com.kafka.demo.config.KafkaConfig.*;
import static org.apache.kafka.clients.consumer.ConsumerConfig.*;

@Configuration
public class KafkaConsumerConfig {

    @Bean
    @Primary
    public Map<String, Object> newConsumerConfig(){
        Map<String, Object> props = new HashMap();

        props.put(BOOTSTRAP_SERVERS_CONFIG, KAFKA_BOOTSTRAP_SERVERS);
        props.put(GROUP_ID_CONFIG, KVRM_GROUP);
        props.put(KEY_DESERIALIZER_CLASS_CONFIG, KEY_DESERIALIZER);
        // props.put(VALUE_DESERIALIZER_CLASS_CONFIG, VALUE_DESERIALIZER);
       // props.put(VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class.getName());
        props.put("spring.deserializer.value.delegate.class", JsonDeserializer.class.getName());
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "com.kafka.demo.dto"); // replace with your package if different
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, Product.class.getName());

        // Set JsonDeserializer for the value deserialization with Product as target class
        props.put(VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        // props.put(TRUSTED_PACKAGES, CONSUMER_TRUSTED_PACKAGE);
        return props;
    }

    @Bean
    @Primary
    public ConsumerFactory<String, Object> newConsumerFactory(){
         return new DefaultKafkaConsumerFactory<>(newConsumerConfig());
    }

    @Bean
    @Primary
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Object>> kafkaListnerContainerFactory(){
        ConsumerFactory<String, Object>  consumerFactory = newConsumerFactory();
        ConcurrentKafkaListenerContainerFactory<String, Object> factory =
            new ConcurrentKafkaListenerContainerFactory<>();
       // System.out.println("Final consumer config: " + props);
        System.out.println("############## Final consumer config:: " +  consumerFactory.getConfigurationProperties());

//        factory.getContainerProperties().setMessageConverter(new JsonMessageConverter());
//        factory.getContainerProperties().getKafkaConsumerProperties().put(VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        factory.setConsumerFactory(consumerFactory);
        return factory;
    }
}