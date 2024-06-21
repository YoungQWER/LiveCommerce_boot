package com.shop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 클라이언트로부터 메시지를 받을 endpoint를 /app으로 지정
        registry.setApplicationDestinationPrefixes("/app");
        // /topic으로 시작하는 주제를 가진 클라이언트에게 메시지 전달
        registry.enableSimpleBroker("/topic");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // /chat으로 끝나는 endpoint를 등록하고 클라이언트가 연결할 수 있게 함
        registry.addEndpoint("/chat").withSockJS();
    }
}
