package com.fincodehub.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBoot3WebsocketApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot3WebsocketApplication.class, args);
    }

}
