package com.example.Servlet;

import org.hibernate.Session;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebFilter(filterName = "RequestLoggingFilter")
public class RequestLoggingFilter implements Filter {
    private ServletContext context;

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {


        HttpServletRequest httpRequest = (HttpServletRequest) request;
        System.out.println("I M DOING FILTERING: FROM " + httpRequest.getRequestURI());
        System.out.println("TO" + httpRequest.getRequestURL());
        HttpSession session = httpRequest.getSession(false);

        boolean isLoggedIn = (session != null && session.getAttribute("user") != null);

        String loginURI = httpRequest.getContextPath() + "/LoginController";
        boolean isLoginRequest = httpRequest.getRequestURI().equals(loginURI);
        boolean isLoginPage = httpRequest.getRequestURI().endsWith("login.jsp");

        boolean notRequiredLogin = httpRequest.getRequestURL().toString().contains("top10.jsp") ||
                httpRequest.getRequestURL().toString().contains("Top10URLController");
        if (isLoggedIn && (isLoginRequest || isLoginPage)) {
            System.out.println("case1");
            // the user is already logged in and he's trying to login again
            // then forwards to his homepage
            RequestDispatcher dispatcher = request.getRequestDispatcher("/success.jsp");
            dispatcher.forward(request, response);

        } else if (isLoggedIn || isLoginRequest || notRequiredLogin) {
            System.out.println("case2");
            // continues the filter chain
            // allows the request to reach the destination
            chain.doFilter(request, response);

        } else {
            System.out.println("case3");
            // the user is not logged in, so authentication is required
            // forwards to the Login page
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);

        }

    }
}
