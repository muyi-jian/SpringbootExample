package com.fincodehub.websocket.config;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author YangJian
 * @version 1.0.0
 * @title CustomWebSocketHandler
 * @create 2025/6/9 21:00
 * @description <TODO description class purpose>
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class CustomWebSocketHandler extends TextWebSocketHandler {

    private final WebSocketSessionManager sessionManager;

    /**
     * 用于存储WebSocket会话
     */
    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String sessionId = session.getId();
        sessionManager.add(session);
        log.info("WebSocket连接建立成功：{}", sessionId);
    }


    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("WebSocket收到消息 ==> sessionId = {}, payload = {} ", session.getId(), message.getPayload());

        boolean open = session.isOpen();
        if (!open) {
            log.info("WebSocket发送消息失败 ==> sessionId = {} 不在线 ", session.getId());
            return;
        }

        try {
            /**
             * TODO 发送回复消息
             */
            String replyMessage = "服务器 -> 客户端 收到了，消息" + payload;
            session.sendMessage(new TextMessage(replyMessage));
        } catch (IOException e) {
            log.info("WebSocket发送消息异常 ==> sessionId = {}, e = ", session.getId(), e);
        }

    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.info("WebSocket传输错误 ==> sessionId = {}, exception = {} ", session.getId(), exception.getMessage());
        // 传输错误,关闭连接
        this.afterConnectionClosed(session, CloseStatus.PROTOCOL_ERROR);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("WebSocket连接关闭 ==> sessionId = {}, CloseStatus = {} ", session.getId(), status);
        sessionManager.remove(session);
    }

    /**
     * 广播发送消息给所有连接的 WebSocket客户端
     *
     * @param message
     */
    public void sendMessageByBroadcast(String message) {
        sessionManager.getAllSession()
                .stream().filter(WebSocketSession::isOpen)
                .forEach(session -> {
                    try {
                        session.sendMessage(new TextMessage(message));
                    } catch (IOException e) {
                        log.info("WebSocket 广播发送消息异常 ==> sessionId = {}, e = ", session.getId(), e);
                    }
                });
    }

    @Scheduled(fixedRate = 1000) // 每10秒发送一次心跳，需要启动类或配置类上添加@EnableScheduling
    public void sendHeartbeat() {
        String heartbeat = "heartbeat";
        System.out.println("发送心跳消息");
        sessionManager.getAllSession()
                .stream().filter(WebSocketSession::isOpen)
                .forEach(session -> {
                    try {
                        session.sendMessage(new TextMessage(heartbeat));
                    } catch (IOException e) {
                        log.info("WebSocket 发送心跳消息异常 ==> sessionId = {}, e = ", session.getId(), e);
                    }
                });
    }


    public void sendMessageWithRetry(WebSocketSession session, String message, int maxRetries) {
        int retryCount = 0;
        while (retryCount < maxRetries) {
            try {
                session.sendMessage(new TextMessage(message));
                return;
            } catch (IOException e) {
                retryCount++;
                log.error("消息发送失败，尝试重试 {}/{}", retryCount, maxRetries);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
        log.error("消息发送失败，达到最大重试次数");
    }
}
