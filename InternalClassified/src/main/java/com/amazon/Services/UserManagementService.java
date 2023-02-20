package com.amazon.Services;

import com.amazon.DB.UserDAO;
import com.amazon.Model.User;

import java.util.List;
import java.util.Scanner;

public class UserManagementService {
    private static UserManagementService userManagementService = new UserManagementService();
    private UserManagementService(){}
    public static UserManagementService getInstance(){
        return userManagementService;
    }
    UserDAO userDAO = new UserDAO();
    Scanner scanner = new Scanner(System.in);
    public void ActivateDeactivateUser()
    {
        List<User> userList = userDAO.retrieve();
        if(userList.size()==0)
        {
            System.err.println("There are no users found!");
        }else {
            for(User user:userList)
            {
                user.prettyPrint();
            }
            System.out.println("Enter the UserId of user to Activate/Deactivate::");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.println("1. Activate");
            System.out.println("2. Deactivate");
            System.out.println("3. Cancel");
            System.out.println("Enter your choice:");
            int status = Integer.parseInt(scanner.nextLine());
            if(status==3) return;
            User newUser = new User();
            newUser = getUserById(id);
            newUser.status=status;
            if(userDAO.update(newUser)>0)
            {
                System.out.println("Update is successful");
            }else{
                System.err.println("Something went wrong, while updating user status!");
            }
        }
    }

    public User getUserById(int userId)
    {
        return userDAO.retrieve("SELECT * FROM [User] WHERE id="+userId).get(0);
    }
}
