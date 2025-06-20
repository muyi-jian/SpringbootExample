package com.yj.springboot3actuator.entity;


/**
 * @author YangJian
 * @version 1.0.0
 * @title HttpLog
 * @create 2025/6/20 11:57
 * @description <TODO description class purpose>
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.actuate.web.exchanges.HttpExchange;

import java.net.URI;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HttpLog {
    private LocalDateTime timestamp;
    private Request request;
    private Response response;
    private HttpExchange.Principal principal;
    private Duration timeTaken;

    @Getter
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {
        private URI uri;
        private String remoteAddress;
        private String method;
        private Map<String, List<String>> headers;
    }
    @Getter
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static final class Response {
        private int status;
        private Map<String, List<String>> headers;
    }
}