package com.Spring.Chat.Kafka.Sockets.Application.Kafka_Socket_Chat_App.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
       //Client for the chat to connect with the server using WebSockets
        registry.addEndpoint("/chat").setAllowedOriginPatterns("*").withSockJS();//we give this endPoint from Front-End
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/message");
        registry.enableSimpleBroker("/topic/");
         }
}
