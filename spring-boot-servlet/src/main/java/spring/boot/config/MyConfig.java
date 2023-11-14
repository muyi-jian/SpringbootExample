package spring.boot.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.boot.servlet.RegisterBeanServlet;

/**
 * @author yangjian
 * @date 2022/11/25 9:07
 */
@Configuration
public class MyConfig {
    @Bean
    public ServletRegistrationBean<RegisterBeanServlet> servletBean() {
        ServletRegistrationBean<RegisterBeanServlet> registrationBean = new ServletRegistrationBean<>();
        registrationBean.addUrlMappings("/register");
        registrationBean.setServlet(new RegisterBeanServlet());
        return registrationBean;
    }
}
