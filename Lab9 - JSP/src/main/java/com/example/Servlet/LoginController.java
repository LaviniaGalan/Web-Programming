package com.example.Servlet;

import com.example.Adapter.DBManager;
import com.example.Model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginController", value = "/LoginController")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        RequestDispatcher rd = null;

        DBManager dbManager = new DBManager();

        User result = dbManager.login(username, password);
        if (result != null) {
            rd = request.getRequestDispatcher("/success.jsp");
            HttpSession session = request.getSession();
            session.setAttribute("user", result);
        } else {
            rd = request.getRequestDispatcher("/login.jsp");
        }
        rd.forward(request, response);
    }

}
