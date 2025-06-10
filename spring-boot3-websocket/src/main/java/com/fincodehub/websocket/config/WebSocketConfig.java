package com.fincodehub.websocket.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @author YangJian
 * @version 1.0.0
 * @title WebSocketConfig
 * @create 2025/6/9 20:59
 * @description <TODO description class purpose>
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Autowired
    private CustomWebSocketHandler customWebSocketHandler;
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(customWebSocketHandler, "/websocket")
                .setAllowedOrigins("*"); // 允许跨域访问
    }

}