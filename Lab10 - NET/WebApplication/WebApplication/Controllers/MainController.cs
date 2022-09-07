using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using WebApplication.Models;
using WebApplication.Data;
using Microsoft.EntityFrameworkCore;
using System.Diagnostics;
using WebApplication.Utils;
using Microsoft.AspNetCore.Http;


namespace WebApplication.Controllers
{
    public class MainController : Controller
    {
        private readonly DBContext _context;
        private DBManager dbManager;
        public MainController(DBContext context)
        {
            _context = context;
            dbManager = new DBManager(_context);
        }
        public IActionResult Index()
        {
            if (isLoggedIn())
            {
                return View();
            }
            else
            {
                return RedirectToAction("Index", "Login");
            }
                
        }

        public bool isLoggedIn()
        {
            bool result = true;
            if (HttpContext.Session.GetString("loggedIn") == null ||
                !HttpContext.Session.GetString("loggedIn").Equals("true"))
                result = false;
            return result;
        }
        
        public IActionResult AddUser()
        {
            if (isLoggedIn())
            {
                return View("AddUser");
            }
            else
            {
                return RedirectToAction("Index", "Login");
            }
        }

        
        [Route("Main/UpdateUser/{id}")]
        public IActionResult UpdateUser(int id)
        {
            if (! isLoggedIn())
            {
                return RedirectToAction("Index", "Login");
            }

            UpdateUserModel updateModel = new UpdateUserModel(id, _context);
            Debug.WriteLine(updateModel);
            Debug.WriteLine(updateModel.UpdatedUser.UserId);
            Debug.WriteLine(updateModel.UpdatedUser.Username);
            return View("UpdateUser", updateModel);
        }

        [HttpGet]
        public string GetUsers()
        {
            if (!isLoggedIn())
                return "Invalid operation; please log in.";

            List<User> userList = dbManager.GetUsers();
            MainConverter converter = new MainConverter();
            return converter.UserListToHTMLTable(userList);
        }


        [HttpDelete]
        public string DeleteUser(int userId)
        {
            if (!isLoggedIn())
                return "Invalid operation; please log in.";

            string result = "User deleted!";
            try
            {
                ValidateId(userId);
                dbManager.DeleteUser(userId);
                return result;
            }
            catch(Exception e)
            {
                result = "Could not delete the user:\n> " + e.Message;
                return result;
            }
        }

        [HttpPost]
        public string AddUserSubmit(string name, string username, string password, string age, string role, string email, string webpage)
        {
            if (!isLoggedIn())
                return "Invalid operation; please log in.";

            string result = "User was added!";
            try
            {
                ValidateFields(name, username, password, age, role, email, webpage);
                dbManager.AddUser(name, username, password, age, role, email, webpage);
                return result;
            }
            catch(Exception e)
            {
                result = "Could not add the user:\n> " + e.Message;
                return result;
            }
        }

        [HttpPost]
        public string UpdateUserSubmit(int userId, string name, string username, string password, string age, string role, string email, string webpage)
        {
            if (!isLoggedIn())
                return "Invalid operation; please log in.";

            string result = "User was updated!";
            try
            {
                ValidateId(userId);
                ValidateFields(name, username, password, age, role, email, webpage);
                dbManager.UpdateUser(userId, name, username, password, age, role, email, webpage);
                return result;
            }
            catch (Exception e)
            {
                result = "Could not update the user:\n> " + e.Message;
                return result;
            }
        }


        public void ValidateFields(string name, string username, string password, string age, string role, string email, string webpage)
        {
            if (name == null || name.Length == 0)
            {
                throw new Exception("Name cannot be empty!");
            }
            if (username == null || username.Length == 0)
            {
                throw new Exception("Username cannot be empty!");
            }
            if (password == null ||  password.Length == 0)
            {
                throw new Exception("Password cannot be empty!");
            }
            if (age == null || age.Length == 0 || ! age.All(char.IsDigit))
            {
                throw new Exception("Age must be a natural number!");
            }
            if (role == null || role.Length == 0)
            {
                throw new Exception("Role cannot be empty!");
            }
            if (email == null || email.Length == 0)
            {
                throw new Exception("Email cannot be empty!");
            }
            if (webpage == null || webpage.Length == 0)
            {
                throw new Exception("Webpage cannot be empty!");
            }
        }

        public void ValidateId(int userId)
        {
            if (userId <= 0)
            {
                throw new Exception("Id cannot be negative!");
            }
        }

    }
}
