package com.example.Servlet;

import com.example.Adapter.DBManager;
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

@WebServlet(name = "TopNController", value = "/TopNController")
public class TopNController extends HttpServlet {

    private JSONArray getTopNUrls(int n) {
        DBManager dbManager = new DBManager();
        List<URLWithFreq> urlList = dbManager.getTopNUrls(n);
        JSONArray jsonArray = new JSONArray();
        urlList.forEach(url -> {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put( "urlAddress", url.getUrlAddress());
            jsonObject.put( "frequency", url.getFrequency());
            jsonArray.add(jsonObject);
        });
        return jsonArray;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");

            System.out.println("compute");
            int n = Integer.parseInt(request.getParameter("n"));
            JSONArray array = getTopNUrls(n);
            System.out.println(array);
            response.setContentType("application/json");
            PrintWriter out = new PrintWriter(response.getOutputStream());
            out.println(array.toString());
            out.flush();


    }
}
