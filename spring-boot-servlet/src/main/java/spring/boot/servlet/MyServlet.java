package spring.boot.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 使用注解的方式来定义并注册一个自定义Servlet
 * @author yangjian
 * @date 2022/11/25 8:50
 */
@WebServlet(urlPatterns = "/annotation")
public class MyServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        PrintWriter writer = resp.getWriter();
        writer.write("[MyServlet] welcome " + name);
        writer.flush();
        writer.close();
    }
}
