package com.amazon.View;

import com.amazon.DB.UserDAO;
import com.amazon.Model.User;
import com.amazon.Services.ClassifiedManagementService;
import com.amazon.Services.OrderManagementService;
import com.amazon.Services.passwordEncoder;
import com.amazon.Session.ICSession;

import java.util.Date;

public class UserMenu extends Menu{
    passwordEncoder encoder = passwordEncoder.getInstance();
    private static UserMenu menu = new UserMenu();
    public static UserMenu getInstance()
    {
        return menu;
    }
    UserDAO userDAO = new UserDAO();
    ClassifiedManagementService classifiedManagementService = ClassifiedManagementService.getInstance();
    OrderManagementService orderManagementService = OrderManagementService.getInstance();
    private UserMenu(){

    }

    public void showMenu(){
        System.out.println("Navigating to User Menu..");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Cancel");
        System.out.println("Enter your choice: ");
        int choice = Integer.parseInt(scanner.nextLine());
        boolean result =false;
        User user = new User();
        if(choice == 1)
        {
            System.out.println("Enter Your Name:");
            user.name = scanner.nextLine();
            System.out.println("Enter Your Email:");
            user.email = scanner.nextLine();
            System.out.println("Enter Password:");
            String password = scanner.nextLine();
            user.password = encoder.encode(password);
            System.out.println("Enter Your Phone: ");
            user.phone = scanner.nextLine();
            System.out.println("Enter Your gender: ");
            user.gender = scanner.nextLine();
            System.out.println("Enter Your address: ");
            user.address = scanner.nextLine();
            System.out.println("Enter Your department: ");
            user.department = scanner.nextLine();
            user.type = 2;
            user.status =1;
            result = auth.registerUser(user);
            if(result)
            {
                System.out.println("Successfully registered.");
                user= userDAO.retrieve("SELECT * from [User] Where email='"+user.email+"'").get(0);

            }else{
                System.err.println("Something went wrong in the registration process!");
            }

        }else if(choice == 2){
            System.out.println("Enter Your Email:");
            user.email = scanner.nextLine();
            System.out.println("Enter Your Password:");
            user.password = encoder.encode(scanner.nextLine());
            result = auth.loginUser(user);
        }else if(choice == 3)
        {
            System.out.println("Thank You for Using Internal Classified App");
        }else{
            System.err.println("Invalid Choice..");
            System.out.println("Thank You for Using Internal Classified App");
        }

        if(result && user.type == 2)
        {
            ICSession.user = user;
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
            System.out.println("Welcome to User Section of Internal Classified.");
            System.out.println("Hello, "+user.name);
            System.out.println("Its: "+new Date());
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
            boolean quit = false;

            while(true)
            {
                System.out.println("1. Manage Your Account");
                System.out.println("2. Post a Classified");
                System.out.println("3. View all Classified");
                System.out.println("4. Buy/Sell a Classified");
                System.out.println("5. Quit the User Section of Internal Classified");
                System.out.println("Select an Option");
                int uchoice = Integer.parseInt(scanner.nextLine());
                switch (uchoice)
                {
                    case 1:
                        System.out.println("My Account");
                        user.prettyPrint();
                        System.out.println("Do you wish to update Account (1: update 0: cancel)");

                        choice = Integer.parseInt(scanner.nextLine());


                        if(choice == 1) {

                            System.out.println("Enter the details if you wish to change it else leave it blank and press Enter to go to next:)");
                            System.out.println("Enter Your Name:");
                            String name = scanner.nextLine();
                            if(!name.isEmpty()) {
                                user.name = name;
                            }

                            System.out.println("Enter Your Phone:");
                            String phone = scanner.nextLine();
                            if(!phone.isEmpty()) {
                                user.phone = phone;
                            }

                            System.out.println("Enter Your Password:");
                            String password = scanner.nextLine();
                            if(!password.isEmpty()) {
                                user.password = password;
                            }

                            System.out.println("Enter Your Address:");
                            String address = scanner.nextLine();
                            if(!address.isEmpty()) {
                                user.address = address;
                            }

                            System.out.println("Enter Your Department:");
                            String department = scanner.nextLine();
                            if(!department.isEmpty()) {
                                user.department = department;
                            }

                            if(auth.updateUser(user)) {
                                System.out.println("Profile Updated Successfully");
                            }else {
                                System.err.println("Profile Update Failed...");
                            }

                        }
                        break;
                    case 2: classifiedManagementService.postClassified();
                        break;
                    case 3:classifiedManagementService.viewAllClassified(false);
                        break;
                    case 4:
                        System.out.println("1. Buy");
                        System.out.println("2. Sell");
                        System.out.println("Enter you choice");
                        int buySellChoice = Integer.parseInt(scanner.nextLine());
                        switch (buySellChoice)
                        {
                            case 1:orderManagementService.Buy();
                            break;
                            case 2:orderManagementService.Sell();
                                break;
                            default:
                                System.err.println("Invalid Choice...");
                                break;
                        }
                        break;
                    case 5:
                        System.out.println("Thank You for Using Internal Classified!!");
                        quit = true;
                        break;
                    default:
                        System.err.println("Invalid Choice..");
                        break;
                }
                if(quit){
                    break;
                }
            }
        }else {
            System.err.println("Authentication Failed..");
        }
    }

}
