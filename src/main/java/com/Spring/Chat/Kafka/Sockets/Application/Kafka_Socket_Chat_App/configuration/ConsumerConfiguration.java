package com.Spring.Chat.Kafka.Sockets.Application.Kafka_Socket_Chat_App.configuration;

import com.Spring.Chat.Kafka.Sockets.Application.Kafka_Socket_Chat_App.constant.KafkaConstant;
import com.Spring.Chat.Kafka.Sockets.Application.Kafka_Socket_Chat_App.model.Message;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class ConsumerConfiguration {

    @Bean
    public Map<String,Object> consumerConfig()
    {
//        public static final String TOPIC="messenger";
//        public static final String GROUP_ID="messenger_sandbox";
//        public static final String BROKER="localhost:9092";//KAFKA BROKER

        Map<String,Object> config=new HashMap<>();
        //Assign ConsumerConfig BootStrap_Server
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstant.BROKER);//kafka server url
        config.put(ConsumerConfig.GROUP_ID_CONFIG,KafkaConstant.GROUP_ID);//group the data into 1 category/particular consumer service(unique session)
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);//decode data for key
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);//decode data for value
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"latest");//always pick latest one from Queue
        return config;
    }

    @Bean
    public ConsumerFactory<String,Message> consumerFactory()
    {
        //Method for creating a Producer
        return new DefaultKafkaConsumerFactory<>(consumerConfig(),new StringDeserializer(),new JsonDeserializer<>(Message.class));
    }


  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, Message> kafkaListenerContainerFactory()
  {
      //Concurrent Message to KAfka Listner factory
    ConcurrentKafkaListenerContainerFactory<String,Message> factory=new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    return factory;
  }






}
