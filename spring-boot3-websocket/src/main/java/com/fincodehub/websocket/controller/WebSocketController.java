package com.fincodehub.websocket.controller;


import com.fincodehub.websocket.config.CustomWebSocketHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author YangJian
 * @version 1.0.0
 * @title WebSocketController
 * @create 2025/6/9 21:03
 * @description <TODO description class purpose>
 */
@Controller
@RequestMapping("/api")
public class WebSocketController {

    private final CustomWebSocketHandler webSocketHandler;

    public WebSocketController(CustomWebSocketHandler webSocketHandler) {
        this.webSocketHandler = webSocketHandler;
    }

    @PostMapping("/websocket")
    public ResponseEntity<String> broadcastMessage(@RequestBody String message) {
        webSocketHandler.sendMessageByBroadcast(message);
        return ResponseEntity.ok("消息广播成功");
    }



}
