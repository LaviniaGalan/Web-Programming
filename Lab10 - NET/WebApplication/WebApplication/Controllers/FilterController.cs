using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using WebApplication.Data;
using WebApplication.Models;
using WebApplication.Utils;
using Microsoft.AspNetCore.Http;

namespace WebApplication.Controllers
{
    public class FilterController : Controller
    {
        private readonly DBContext _context;
        private DBManager dbManager;

        public IActionResult Index()
        {
            if (isLoggedIn())
                return View();
            else
                return RedirectToAction("Index", "Login");
        }

        public FilterController(DBContext context)
        {
            _context = context;
            dbManager = new DBManager(_context);
        }

        [HttpPost]
        public string FilterUsers(string filterType, string filterText)
        {
            if (!isLoggedIn())
                return "Invalid operation; please log in.";

            List<User> usersList = new List<User>();
            if (filterType.ToLower().Equals("role"))
            {
                usersList = dbManager.FilterUsersByRole(filterText);
            }
            else if (filterType.ToLower().Equals("name"))
            {
                usersList = dbManager.FilterUsersByName(filterText);
            }
            else
            {
                return "Bad type for filter!";
            }

            FilterConverter converter = new FilterConverter();
            return converter.UserListToHTMLTable(usersList);
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
