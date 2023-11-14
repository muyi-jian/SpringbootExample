package spring.boot.servlet;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author yangjian
 * @date 2022/11/25 9:30
 */
@Component
@Order(-10000)  //主要用来控制配置类的加载顺序，值越小，越先执行
public class BeanServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        PrintWriter writer = resp.getWriter();
        writer.write("[BeanServlet] welcome " + name);
        writer.flush();
        writer.close();
    }
}
