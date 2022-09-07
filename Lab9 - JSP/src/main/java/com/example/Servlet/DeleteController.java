package com.example.Servlet;

import com.example.Adapter.DBManager;
import com.example.Model.User;
import org.json.simple.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DeleteController", value = "/DeleteController")
public class DeleteController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String urlAddress = request.getParameter("URLAddress");
        int userId = ((User)request.getSession().getAttribute("user")).getUserId();

        System.out.println(urlAddress);
        System.out.println(userId);

        DBManager dbManager = new DBManager();
        String result = dbManager.deleteURL(userId, urlAddress);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", result);

        response.setContentType("application/json");
        PrintWriter out = new PrintWriter(response.getOutputStream());
        out.println(jsonObject.toString());
        out.flush();
    }
}
