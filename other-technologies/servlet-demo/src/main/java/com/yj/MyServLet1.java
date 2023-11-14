package com.yj;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class MyServLet1 extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println(config.getServletName());
        System.out.println(config.getInitParameter("name"));

        System.out.println(config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 获取请求参数
        Object key2 = req.getAttribute("key2");
        System.out.println("key2====="+key2);

        ServletContext servletContext = getServletConfig().getServletContext();
        String name = servletContext.getInitParameter("name");
        System.out.println("init-name======"+name);

        String contextPath = servletContext.getContextPath();
        System.out.println("工程路径===="+contextPath);
        System.out.println("工程路径===="+servletContext.getRealPath("/"));

        System.out.println("servletContext==="+servletContext);
        System.out.println("key1=="+servletContext.getAttribute("key1"));
        //
        servletContext.setAttribute("key1","val1");
        System.out.println("MyServLet1===key1=="+servletContext.getAttribute("key1"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置请求体的字符集为 UTF-8，从而解决 post 请求的中文乱码问题
        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String age = req.getParameter("age");
        String[] radios = req.getParameterValues("radios");
        String[] checkboxs = req.getParameterValues("checkboxs");
        String shuoming = req.getParameter("shuoming");

        System.out.println("username===="+username);
        System.out.println("password===="+password);
        System.out.println("age===="+age);
        System.out.println("shuoming===="+shuoming);

        System.out.println("radios====="+ Arrays.asList(radios));
        System.out.println("checkboxs====="+ Arrays.asList(checkboxs));
    }
}
