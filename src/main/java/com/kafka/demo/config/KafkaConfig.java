package com.kafka.demo.config;

public class KafkaConfig {

    public final static String PRODUCT_TOPIC = "product-topic";

    public final static String KVRM_TOPIC_1 = "kvrm-topic-1";
    public final static String KVRM_GROUP = "kvrm-group";

    public final static String KAFKA_BOOTSTRAP_SERVERS = "daaji:9092";
    public final static String KEY_SERIALIZER = "org.apache.kafka.common.serialization.StringSerializer";
    public final static String VALUE_SERIALIZER ="org.springframework.kafka.support.serializer.JsonSerializer";

    public final static String KEY_DESERIALIZER = "org.apache.kafka.common.serialization.StringDeserializer";
    public final static String VALUE_DESERIALIZER ="org.springframework.kafka.support.serializer.JsonDeserializer";
    public static final String CONSUMER_TRUSTED_PACKAGE = "com.kafka.demo.dto";


}
