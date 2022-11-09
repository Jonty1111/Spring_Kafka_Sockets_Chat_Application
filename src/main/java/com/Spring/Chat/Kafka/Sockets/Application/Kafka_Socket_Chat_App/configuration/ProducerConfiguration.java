package com.Spring.Chat.Kafka.Sockets.Application.Kafka_Socket_Chat_App.configuration;


import com.Spring.Chat.Kafka.Sockets.Application.Kafka_Socket_Chat_App.constant.KafkaConstant;
import com.Spring.Chat.Kafka.Sockets.Application.Kafka_Socket_Chat_App.model.Message;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import  static  com.Spring.Chat.Kafka.Sockets.Application.Kafka_Socket_Chat_App.constant.KafkaConstant.*;
import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class ProducerConfiguration {

    @Bean //Configuration for Producer
    public Map<String, Object> producerConfig()
    {
        //
        Map<String,Object> config=new HashMap<>();
        //Created a config variable and try to put the BootStrap server config
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BROKER);//Kafka Server URL
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        System.out.println(config);
        return config;


    }


    @Bean
    public ProducerFactory<String, Message> producerFactory()
    {
        return new DefaultKafkaProducerFactory<>(
                producerConfig());//Method for creating Producer Instances on kafka Broker
    }



    @Bean
    public KafkaTemplate<String, Message> kafkaTemplate(){
        return  new KafkaTemplate<>(producerFactory());
    }

}
