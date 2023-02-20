package com.amazon.Services;

import com.amazon.DB.UserDAO;
import com.amazon.Model.User;

import java.util.List;

public class AuthenticationService {
    private static AuthenticationService service = new AuthenticationService();
    public static AuthenticationService getInstance(){
        return service;
    }

    UserDAO dao = new UserDAO();
    private AuthenticationService(){

    }

    public boolean loginUser(User user)
    {
        String sql = "SELECT * FROM [USER] WHERE email='"+user.email+"' AND password ='"+user.password+"'";
        List<User> users = dao.retrieve(sql);
        if(users.size()>0)
        {
            User u = users.get(0);
            user.id = u.id;
            user.name = u.name;
            user.email = u.email;
            user.password = u.password;
            user.phone = u.phone;
            user.gender = u.gender;
            user.address = u.address;
            user.department = u.department;
            user.type = u.type;
            user.status = u.status;
            user.createdOn = u.createdOn;
            return true;
        }
        return false;
    }

    public boolean registerUser(User user)
    {
        return dao.insert(user) > 0;
    }

    public boolean updateUser(User user)
    {
        return dao.update(user)>0;
    }

}
