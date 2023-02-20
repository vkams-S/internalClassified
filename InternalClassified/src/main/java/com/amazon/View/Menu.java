package com.amazon.View;

import com.amazon.DB.DB;
import com.amazon.Services.AuthenticationService;

import java.util.Scanner;

public class Menu {
    AuthenticationService auth = AuthenticationService.getInstance();
    Scanner scanner = new Scanner(System.in);
    public void showMainMenu(){
        while(true)
        {
            System.out.println("1. Admin");
            System.out.println("2. User");
            System.out.println("3. Quit");
            System.out.println("Select an option");
            int choice = Integer.parseInt(scanner.nextLine());
            if(choice == 3)
            {
                System.out.println("Thank you for using Internal Classified App");
                DB db = DB.getInstance();
                db.closeConnection();
                scanner.close();
                break;
            }
            if(choice==1 || choice==2)
            {
                //Call Menufactory to redirect to appropriate menu according choice
                MenuFactory.getMenu(choice).showMenu();
            }else {
                System.out.println("Invalid choice..please choose a valid option.");
            }
        }
    }
    public void showMenu(){
        System.out.println("Showing the Menu..");
    }
}
