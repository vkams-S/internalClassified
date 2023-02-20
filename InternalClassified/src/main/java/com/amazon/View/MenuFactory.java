package com.amazon.View;

public class MenuFactory {
    public static Menu getMenu(int type)
    {
        if(type==1){
            // return the AdminMenu object
            return AdminMenu.getInstance();
        } else if (type==2) {
            // return the UserMenu object
            return UserMenu.getInstance();
        }
        return null;
    }
}
