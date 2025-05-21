package com.fincodehub.config;


import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author YangJian
 * @version 1.0.0
 * @title SwaggerConfig
 * @create 2025/5/21 20:45
 * @description <TODO description class purpose>
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("default")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI swaggerOpenAPI(){
        return new OpenAPI()
                .info(new Info().title("FCH中心")
                        .contact(new Contact())
                        .description("FCH是编程和金融理财交汇中心")
                        .version("版本v.1.0")
                        .license(new License().name("Apache 2.0").url("https://xxxx.xxx.xxx")))
                .externalDocs(new ExternalDocumentation()
                        .description("外部文档")
                        .url("https://www.fincodehub.com"));
    }
}

