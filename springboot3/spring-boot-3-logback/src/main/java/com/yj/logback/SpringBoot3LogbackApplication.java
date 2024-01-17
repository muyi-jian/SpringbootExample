package com.yj.logback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@Slf4j
public class SpringBoot3LogbackApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringBoot3LogbackApplication.class, args);

		int length = context.getBeanDefinitionNames().length;
		log.trace("trace:Spring boot启动初始化了 {} 个 Bean", length);
		log.debug("debug:Spring boot启动初始化了 {} 个 Bean", length);
		log.info("info:Spring boot启动初始化了 {} 个 Bean", length);
		log.warn("warn:Spring boot启动初始化了 {} 个 Bean", length);
		log.error("error:Spring boot启动初始化了 {} 个 Bean", length);
	}

}
