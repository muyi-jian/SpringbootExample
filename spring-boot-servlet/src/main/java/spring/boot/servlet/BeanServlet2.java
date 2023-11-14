package spring.boot.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author yangjian
 * @date 2022/11/25 9:41
 */
//@Component
public class BeanServlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        PrintWriter writer = resp.getWriter();
        writer.write("[BeanServlet2] welcome " + name);
        writer.flush();
        writer.close();
    }
}
