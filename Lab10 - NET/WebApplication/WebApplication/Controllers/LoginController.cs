using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using WebApplication.Data;
using Microsoft.AspNetCore.Http;


namespace WebApplication.Controllers
{
    public class LoginController : Controller
    {
        private readonly DBContext _context;
        private DBManager dbManager;
        public LoginController(DBContext context)
        {
            _context = context;
            dbManager = new DBManager(_context);
        }

        public IActionResult Index()
        {
            if (isLoggedIn())
                return RedirectToAction("Index", "Main");
            return View();
        }

        [HttpPost]
        public IActionResult LoginSubmit(string username, string password)
        {
            try
            {
                dbManager.login(username, password);
                initializeSessionForLogin();
                return RedirectToAction("Index", "Main");
            }
            catch(Exception e)
            {
                return RedirectToAction("Index", "Login");
            }
        }

        [HttpGet]
        public IActionResult LogoutSubmit()
        {
            initializeSessionForLogout();
            return RedirectToAction("Index", "Login");
        }

        public void initializeSessionForLogin()
        {
            HttpContext.Session.Clear();
            HttpContext.Session.SetString("loggedIn", "true");
        }

        public void initializeSessionForLogout()
        {
            HttpContext.Session.Clear();
        }

        public bool isLoggedIn()
        {
            bool result = true;
            if (HttpContext.Session.GetString("loggedIn") == null ||
                !HttpContext.Session.GetString("loggedIn").Equals("true"))
                result = false;
            return result;
        }
    }
}
