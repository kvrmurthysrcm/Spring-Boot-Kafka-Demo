package com.kafka.demo.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import static org.apache.kafka.clients.producer.ProducerConfig.*;

import java.util.HashMap;
import java.util.Map;

import static com.kafka.demo.config.KafkaConfig.*;
import static org.apache.kafka.clients.CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG;

@Configuration
public class KafkaProducerConfig {


    @Bean
    public NewTopic createTopic(){
        System.out.println("Creating the topic:" + KVRM_TOPIC_1);
        return new NewTopic(KVRM_TOPIC_1, 5, (short)1);
    }

    @Bean
    public NewTopic createProductTopic(){
        System.out.println("Creating the topic:"+ PRODUCT_TOPIC);
        return new NewTopic(PRODUCT_TOPIC, 5, (short)1);
    }

    @Bean
    public Map<String, Object> producerConfig(){
        Map<String, Object> props = new HashMap();
        props.put(BOOTSTRAP_SERVERS_CONFIG, KAFKA_BOOTSTRAP_SERVERS);
        props.put(KEY_SERIALIZER_CLASS_CONFIG, KEY_SERIALIZER);
       // props.put(VALUE_SERIALIZER_CLASS_CONFIG, VALUE_SERIALIZER);
        props.put("value.serializer", "org.springframework.kafka.support.serializer.JsonSerializer");
        // props.put("value-serializer", VALUE_SERIALIZER);
        // or other seriaIAlizers, e.g., IntegerSerializer, ByteArraySerializer, etc.
        return props;
    }

    @Bean
    public ProducerFactory<String, Object> producerFactory(){
        Map<String, Object> producerConfig = producerConfig();
        System.out.println("########### @producerFactory()::producerConfig:: " + producerConfig);
        return new DefaultKafkaProducerFactory<>(producerConfig);
    }

    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate(){
        ProducerFactory<String, Object> producerFactory = producerFactory();
        System.out.println("########## @kafkaTemplate():: producerFactory config:: " + producerFactory.getConfigurationProperties());

        return new KafkaTemplate<>(producerFactory);
    }

}