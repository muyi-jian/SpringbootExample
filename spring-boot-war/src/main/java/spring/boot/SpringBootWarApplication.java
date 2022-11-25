package spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringBootWarApplication extends SpringBootServletInitializer {

    /**
     *  sources方法中传入当前启动类即可
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootWarApplication.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(SpringBootWarApplication.class, args);
    }

}
