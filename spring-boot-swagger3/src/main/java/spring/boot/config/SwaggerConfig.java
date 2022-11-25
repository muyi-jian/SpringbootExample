package spring.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * 如果pom中还引用了spring-boot-starter-web，一定要在配置类上增加@EnableWebMvc注解。
 *
 *  @EnableOpenApi是Swagger3.0的注解，默认已经开启，可选。
 * 文档类型选择OAS_30，表示用swagger3.0。
 *
 * 通过enable参数配置来控制swagger的开关，在生产环境中，swagger功能不需要开启。
 * @author yangjian
 * @date 2022/11/24 21:51
 */
@Configuration
@EnableOpenApi
@EnableWebMvc // spring-boot-starter-web冲突会引发启动服务时null，必选
public class SwaggerConfig {
    /**
     * 创建API应用
     * apiInfo() 增加API相关信息
     * 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现，
     * 本例采用指定扫描的包路径来定义指定要建立API的目录。
     *
     * @return
     */
    @Bean
    public Docket desertsApi1() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo("测试——Swagger3.0", "1.0"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("spring.boot.controller"))
                .paths(PathSelectors.any())
                .build()
                .groupName("人员信息")
                .enable(true);
    }

    //再定义一个Docket
    @Bean
    public Docket desertsApi2() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo("测试——Swagger3.0", "1.0"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("spring.boot.controller"))
                .paths(PathSelectors.any())
                .build()
                .groupName("登录")
                .enable(true);
    }

    /**
     * 创建该API的基本信息（这些基本信息会展现在文档页面中）
     * 访问地址：http://ip:port/swagger-ui.html
     *
     * @return
     */
    private ApiInfo apiInfo(String title, String version) {
        return new ApiInfoBuilder()
                .title(title)
                .description("接口测试页面")
                .contact(new Contact("GMS", "http://localhost:8080/swagger-ui/index.html", "1457205312@qq.com"))
                .termsOfServiceUrl("http://localhost:8080/index.html")
                .version(version)
                .build();
    }
}
