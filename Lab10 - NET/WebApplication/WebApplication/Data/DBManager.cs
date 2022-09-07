using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Threading.Tasks;
using WebApplication.Models;

namespace WebApplication.Data
{
    public class DBManager
    {
        private readonly DBContext _context;
        public DBManager(DBContext context)
        {
            _context = context;
        }

        public List<User> GetUsers()
        {
            return _context.Users.Select(x => x).ToList();
        }

        public void DeleteUser(int userId)
        {
            User deletedUser = new User { UserId = userId };
            _context.Entry(deletedUser).State = EntityState.Deleted;
            _context.SaveChanges();
        }

        public void AddUser(string name, string username, string password, string age, string role, string email, string webpage)
        {
            User newUser = new User
            {
                Name = name,
                Username = username,
                Password = password,
                Age = Int32.Parse(age),
                Role = role,
                Email = email,
                Webpage = webpage
            };
            _context.Add(newUser);
            _context.SaveChanges();
        }


        public User FindUserById(int userId)
        {
            User user = _context.Users.Find(userId);
            Debug.WriteLine(user.UserId);
            Debug.WriteLine(user.Username);
            return user;
        }

        public List<User> FilterUsersByRole(string role)
        {
            List<User> result = _context.Users.Where(u => u.Role.ToLower().Equals(role.ToLower())).ToList();
            return result;
        }

        public List<User> FilterUsersByName(string name)
        {
            List<User> result = _context.Users.Where(u => u.Name.ToLower().Contains(name.ToLower())).ToList();
            return result;
        }

        public void UpdateUser(int userId, string name, string username, string password, string age, string role, string email, string webpage)
        {
            User user = this.FindUserById(userId);
            user.Name = name;
            user.Username = username;
            user.Password = password;
            user.Age = Int32.Parse(age);
            user.Role = role;
            user.Email = email;
            user.Webpage = webpage;

            _context.Attach(user);
            _context.Entry(user).Property(u => u.Name).IsModified = true;
            _context.Entry(user).Property(u => u.Username).IsModified = true;
            _context.Entry(user).Property(u => u.Password).IsModified = true;
            _context.Entry(user).Property(u => u.Age).IsModified = true;
            _context.Entry(user).Property(u => u.Role).IsModified = true;
            _context.Entry(user).Property(u => u.Email).IsModified = true;
            _context.Entry(user).Property(u => u.Webpage).IsModified = true;
            _context.SaveChanges();
        }

        public void login(string username, string password)
        {
            List<int> result = _context.Admins.Where(u => u.Username.Equals(username) && u.Password.Equals(password)).Select(u => u.AdminId).ToList();
            if (result.Count() == 0)
            {
                throw new Exception("Invalid credentials!");
            }
        }
    }
}
