package com.yj.springboot3actuator.config;


/**
 * @author YangJian
 * @version 1.0.0
 * @title RedisHttpExchangeRepository
 * @create 2025/6/20 11:32
 * @description <TODO description class purpose>
 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yj.springboot3actuator.entity.HttpLog;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.actuate.web.exchanges.HttpExchange;
import org.springframework.boot.actuate.web.exchanges.HttpExchangeRepository;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.List;

@Component
public class RedisHttpExchangeRepository implements HttpExchangeRepository {
    private final StringRedisTemplate stringRedisTemplate;
    private final
    ObjectMapper objectMapper;

    public RedisHttpExchangeRepository(StringRedisTemplate stringRedisTemplate, ObjectMapper objectMapper) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<HttpExchange> findAll() {
        List<String> list = this.stringRedisTemplate.opsForList().range("http:request:list", 0, -1);
        return list.stream().map(str -> {
            try {
                HttpLog log = this.objectMapper.readValue(str, HttpLog.class);
                HttpLog.Request req = log.getRequest();
                HttpExchange.Request request = new HttpExchange.Request(req.getUri(), req.getRemoteAddress(), req.getMethod(), req.getHeaders());
                HttpLog.Response resp = log.getResponse();
                HttpExchange.Response response = new HttpExchange.Response(resp.getStatus(), resp.getHeaders());
                HttpExchange exchange = new HttpExchange(log.getTimestamp().atZone(ZoneId.systemDefault()).toInstant(), request, response, log.getPrincipal(), null, log.getTimeTaken());
                return exchange;
            } catch (
                    JsonProcessingException e) {
            }
            return null;
        }).filter(e -> e != null).toList();
    }

    @Override
    public void add(HttpExchange httpExchange) {
        try {
            HttpLog log = new HttpLog();
            log.setPrincipal(httpExchange.getPrincipal());
            log.setTimestamp(httpExchange.getTimestamp().atZone(ZoneId.systemDefault()).toLocalDateTime());
            log.setTimeTaken(httpExchange.getTimeTaken());
            HttpLog.Request request = new HttpLog.Request();
            BeanUtils.copyProperties(httpExchange.getRequest(), request);
            HttpLog.Response response = new HttpLog.Response();
            BeanUtils.copyProperties(httpExchange.getResponse(), response);
            log.setRequest(request);
            log.setResponse(response);
            this.stringRedisTemplate.opsForList().leftPush("http:request:list", this.objectMapper.writeValueAsString(log));
        } catch (JsonProcessingException e) {
        }
    }
}
