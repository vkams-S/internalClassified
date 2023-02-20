package com.amazon;

import com.amazon.View.Menu;

public class App
{
    public static void main( String[] args )
    {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println( "Welcome to Internal Classified Application");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        Menu menu = new Menu();
        menu.showMainMenu();
    }
}
