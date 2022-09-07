package com.example.Servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.example.Adapter.DBManager;
import com.example.Model.URLWithFreq;
import com.example.Model.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@WebServlet(name = "Top10URLController", value = "/Top10URLController")
public class Top10URLController extends HttpServlet {
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
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        //HttpSession session = req.getSession();
        //Integer userid = Integer.parseInt(session.getAttribute("userid").toString());
        JSONArray array = getTopNUrls(10);
        System.out.println(array);
        response.setContentType("application/json");
        PrintWriter out = new PrintWriter(response.getOutputStream());
        out.println(array.toString());
        out.flush();
    }


}
