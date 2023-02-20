package com.amazon.View;

import com.amazon.Model.User;
import com.amazon.Services.ClassifiedManagementService;
import com.amazon.Services.OrderManagementService;
import com.amazon.Services.UserManagementService;
import com.amazon.Session.ICSession;

import java.util.Date;

public class AdminMenu extends Menu{
    private static AdminMenu menu = new AdminMenu();
    public static AdminMenu getInstance(){
        return menu;
    }

    ClassifiedManagementService classifiedManagementService = ClassifiedManagementService.getInstance();
    OrderManagementService orderManagementService = OrderManagementService.getInstance();
    UserManagementService userManagementService = UserManagementService.getInstance();
    private AdminMenu(){

    }

    public void showMenu(){
        System.out.println("Navigating to Admin Menu..");
        User adminUser = new User();
        System.out.println("Enter Your Email:");
        adminUser.email = scanner.nextLine();
        System.out.println("Enter Your Password:");
        adminUser.password = scanner.nextLine();

        boolean result = auth.loginUser(adminUser);
        if(result && adminUser.type == 1){
            ICSession.user = adminUser;
            System.out.println("************************");
            System.out.println("Welcome to Admin App");
            System.out.println("Hello, "+adminUser.name);
            System.out.println("Its: "+new Date());
            System.out.println("************************");
            boolean quit=false;
            while (true){
                System.out.println("1. Approve/Reject a Classified");
                System.out.println("2. Activate/Deactivate a User");
                System.out.println("3. Add/Remove Classified");
                System.out.println("4. Categories a Classified");
                System.out.println("5. Generate Classified Report");
                System.out.println("6. Quit the Admin App");
                System.out.println("Enter Your Choice:");
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice)
                {
                    case 1: classifiedManagementService.ApproveRejectClassified();
                        break;
                    case 2: userManagementService.ActivateDeactivateUser();
                        break;
                    case 3: classifiedManagementService.AddRemoveClassified();
                        break;
                    case 4: classifiedManagementService.AssignCategory();
                        break;
                    case 5: orderManagementService.ViewOrderReport();
                        break;
                    case 6:
                        System.out.println("Thank You for Using Admin App !!");
                        quit = true;
                        break;
                    default:
                        System.err.println("Invalid Choice..");
                        break;
                }
                if(quit)
                    break;
            }
        }
    }

}
