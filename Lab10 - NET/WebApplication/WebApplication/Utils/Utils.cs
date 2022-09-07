using Microsoft.AspNetCore.Http;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApplication.Models;

namespace WebApplication.Utils
{
    public class MainConverter
    {
        public string UserListToHTMLTable(List<User> usersList)
        {
            string result =
                "<tr><th>Id</th><th>Name</th><th>Username</th>" +
                "<th>Password</th><th>Age</th>" +
                "<th>Role</th><th>Email</th>" +
                "<th>Webpage</th><th>Delete</th>" +
                "<th>Update</th></tr>";

            foreach (User user in usersList)
            {
                result += "<tr><td>" + user.UserId +
                          "</td><td>" + user.Name +
                          "</td><td>" + user.Username +
                          "</td><td>" + user.Password +
                          "</td><td>" + user.Age +
                          "</td><td>" + user.Role +
                          "</td><td>" + user.Email +
                          "</td><td>" + user.Webpage +
                          "</td><td> <button class=\"btn btn-dark deleteButton\">Delete</button>" +
                          "</td><td> <button class=\"btn btn-dark updateButton\">Update</button>" +
                          "</td></tr>";
            }

            return result;
        }
    }


    public class FilterConverter
    {
        public string UserListToHTMLTable(List<User> usersList)
        {
            string result =
                "<tr><th>Id</th><th>Name</th><th>Username</th>" +
                "<th>Password</th><th>Age</th>" +
                "<th>Role</th><th>Email</th>" +
                "<th>Webpage</th></tr>";

            foreach (User user in usersList)
            {
                result += "<tr><td>" + user.UserId +
                          "</td><td>" + user.Name +
                          "</td><td>" + user.Username +
                          "</td><td>" + user.Password +
                          "</td><td>" + user.Age +
                          "</td><td>" + user.Role +
                          "</td><td>" + user.Email +
                          "</td><td>" + user.Webpage +
                          "</td></tr>";
            }

            return result;
        }
    }

}
