package com.example.Servlet;

import com.example.Adapter.DBManager;
import com.example.Model.URL;
import com.example.Model.URLWithFreq;
import com.example.Model.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "MyURLController", value = "/MyURLController")
public class MyURLController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DBManager dbManager = new DBManager();

        User user = (User)request.getSession().getAttribute("user");
        int userId = user.getUserId();

        List<URL> urlList = dbManager.getMyURLs(userId);

        JSONArray jsonArray = new JSONArray();
        urlList.forEach(url -> {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put( "urlAddress", url.getUrlAddress());
            jsonArray.add(jsonObject);
        });

        response.setContentType("application/json");
        PrintWriter out = new PrintWriter(response.getOutputStream());
        out.println(jsonArray.toString());
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String urlAddress = request.getParameter("URLAddress");
        int userId = ((User)request.getSession().getAttribute("user")).getUserId();

        DBManager dbManager = new DBManager();
        String result = dbManager.addURL(userId, urlAddress);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put( "result", result);

        response.setContentType("application/json");
        PrintWriter out = new PrintWriter(response.getOutputStream());
        out.println(jsonObject.toString());
        out.flush();
    }


}
