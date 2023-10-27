package com.yj;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyServLet2 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = getServletContext();
        System.out.println("MyServLet2===key1=="+context.getAttribute("key1"));

        System.out.println("客户端ip地址==="+req.getRemoteHost());
        System.out.println("资源路径==="+req.getRequestURI());
        System.out.println("资源定位符==="+req.getRequestURL());
        System.out.println("请求头==="+req.getHeader("User-Agent"));
        System.out.println("请求方式==="+req.getMethod());

        // 获取请求参数
        String username = req.getParameter("username");
        System.out.println("username====="+username);

        /**
         * 请求转发必须要以斜杠打头，/ 斜杠表示地址为：http://ip:port/工程名/ , 映射到 IDEA 代码的 web 目录
         <br/>
         *
         */
        req.setAttribute("key2","转发");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/myservlet1");
        requestDispatcher.forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
