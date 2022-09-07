using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApplication.Data;

namespace WebApplication.Models
{
    public class UpdateUserModel
    {
        public User UpdatedUser { get; set; }
        private readonly DBContext _context;
        private DBManager dbManager;
        public UpdateUserModel(int userId, DBContext context)
        {
            _context = context;
            dbManager = new DBManager(_context);

            UpdatedUser = dbManager.FindUserById(userId);
        }
    }
}
