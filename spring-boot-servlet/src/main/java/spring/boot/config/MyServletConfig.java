package spring.boot.config;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.stereotype.Component;
import spring.boot.servlet.ContextServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * ServletContextInitializer 主要被 RegistrationBean 实现用于往 ServletContext
 * 容器中注册 Servlet,Filter 或者 EventListener。这些 ServletContextInitializer
 * 的设计目的主要是用于这些实例被 Spring IoC 容器管理
 * @author yangjian
 * @date 2022/11/25 9:25
 */
@Component
public class MyServletConfig implements ServletContextInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        ServletRegistration initServlet = servletContext.addServlet("contextServlet", ContextServlet.class);
        initServlet.addMapping("/context");
    }
}
