package com.yj.swagger.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author YangJian
 * @date 2023/12/31 10:30
 * @description
 */
@Configuration
public class SpringfoxConfig {

    @Bean
    public GroupedOpenApi userApi(){
        return GroupedOpenApi.builder()
                .group("User-api")
                .pathsToMatch("/user/**")
                .build();
    }

    @Bean
    public GroupedOpenApi systemApi(){
        return GroupedOpenApi.builder()
               .group("System-api")
               .pathsToMatch("/system/**")
               .build();
    }

    @Bean
    public OpenAPI webApi(){
        return new OpenAPI()
               .info(new io.swagger.v3.oas.models.info.Info()
                       .title("Spring Boot 3.0 集成 Swagger3")
                       .description("Spring Boot 3.0 集成 Swagger3")
                       .version("1.0")
                       .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation().description("Spring Boot 3.0 集成 Swagger3API").url("http://www.XXXX.com"));
    }
}
