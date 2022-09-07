package com.example.Adapter;

import com.example.Model.URL;
import com.example.Model.URLWithFreq;
import com.example.Model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBManager {

    private String dbURL = "jdbc:mysql://localhost:3306/url_management";
    private String user = "root";
    private String password = "";

    private Statement statement;
    private Connection connection;

    public DBManager(){
        connectToDatabase();
    }

    private void connectToDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(dbURL, user, password);
            statement = connection.createStatement();
        }
        catch (Exception e) {
            System.out.println("Connection error: " + e.getMessage());
        }
    }


    public User login(String username, String password) {

        ResultSet resultSet;
        User result = null;
        try {
            PreparedStatement statement = connection.prepareStatement("select * from users where username = ? and password = ?");
            statement.setString(1, username);
            statement.setString(2, password);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                result = new User();
                result.setUserId(resultSet.getInt("userid"));
                result.setUsername(resultSet.getString("username"));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<URLWithFreq> getTopNUrls(int n) {
        List<URLWithFreq> urlsList = new ArrayList<>();
        ResultSet resultSet;
        try {
            CallableStatement callableStatement = connection.prepareCall("{call GetTopN(?)}");
            callableStatement.setInt(1, n);
            resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                urlsList.add(new URLWithFreq(
                        resultSet.getString("url"),
                        resultSet.getInt("nraparitii")
                ));
            }
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return urlsList;
    }


    public List<URL> getMyURLs(int userId) {
        List<URL> urlsList = new ArrayList<>();
        ResultSet resultSet;
        try {
            String query = "select url from collection where userid = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                urlsList.add(new URL(
                        resultSet.getString("url")
                ));
            }
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return urlsList;
    }


    public String addURL(int userId, String url){
        String result = "URL successfully added!";
        if (url.isEmpty()) {
            result = "URL cannot be empty!";
            return result;
        }
        try {
            CallableStatement callableStatement = connection.prepareCall("{call AddURLForUser(?, ?)}");
            callableStatement.setInt(1, userId);
            callableStatement.setString(2, url);
            callableStatement.executeQuery();
        }
        catch (SQLException throwables) {
            result = "URL already in collection!!!";
        }

        return result;
    }

    public String deleteURL(int userId, String url){
        String result = "URL successfully deleted!";
        try {
            CallableStatement callableStatement = connection.prepareCall("{call DeleteFromCollection(?, ?)}");
            callableStatement.setInt(1, userId);
            callableStatement.setString(2, url);
            callableStatement.executeQuery();
        }
        catch (SQLException throwables) {
            result = "URL could not be deleted!";
        }

        return result;
    }

}
