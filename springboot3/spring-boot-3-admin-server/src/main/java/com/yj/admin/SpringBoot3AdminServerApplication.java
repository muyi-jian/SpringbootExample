package com.yj.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAdminServer
@SpringBootApplication
public class SpringBoot3AdminServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot3AdminServerApplication.class, args);
	}

}
