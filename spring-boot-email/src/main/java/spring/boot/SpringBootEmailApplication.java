package spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import spring.boot.mail.MailProperties;

@EnableConfigurationProperties({MailProperties.class})
@SpringBootApplication
public class SpringBootEmailApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootEmailApplication.class, args);
    }

}
