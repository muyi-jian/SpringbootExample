package com.yj;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class MyServLet implements Servlet {
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init...............");
    }

    public ServletConfig getServletConfig() {
        System.out.println("getServletConfig...............");

        return null;
    }

    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("执行service................");
        HttpServletRequest httpServlet = (HttpServletRequest) servletRequest;
        String method = httpServlet.getMethod();
        if ("POST".equals(method)){
            System.out.println("post..................");
        }else if ("GET".equals(method)){
            System.out.println("GET..................");
        }

    }

    public String getServletInfo() {
        System.out.println("getServletInfo...............");

        return null;
    }

    public void destroy() {
        System.out.println("destroy...............");

    }
}
