package com.fincodehub.websocket.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author YangJian
 * @version 1.0.0
 * @title WebSocketSessionManager
 * @create 2025/6/9 22:18
 * @description <TODO description class purpose>
 */
@Slf4j
@Component
public class WebSocketSessionManager {

    /**
     * 用于存储 WebSocket会话
     * key: sessionId
     * value: WebSocketSession
     */
    private final Map<String, WebSocketSession> wsSessionMap = new ConcurrentHashMap<>();

    /**
     * 添加 WebSocket会话
     *
     * @param session
     */
    public void add(WebSocketSession session) {
        wsSessionMap.put(session.getId(), session);
    }

    /**
     * 移除 WebSocket会话
     *
     * @param session
     */
    public void remove(WebSocketSession session) {
        wsSessionMap.remove(session.getId());
    }

    /**
     * 获取 WebSocket会话
     *
     * @param sessionId
     * @return
     */
    public WebSocketSession get(String sessionId) {
        return wsSessionMap.get(sessionId);
    }

    /**
     * 获取所有 WebSocket会话
     */
    public List<WebSocketSession> getAllSession() {
        Collection<WebSocketSession> sessions = wsSessionMap.values();
        return Optional.ofNullable(sessions).orElse(new ArrayList<>()).stream().toList();
    }
}

