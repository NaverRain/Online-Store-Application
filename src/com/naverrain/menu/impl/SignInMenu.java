package com.naverrain.menu.impl;

import com.naverrain.configs.ApplicationContext;
import com.naverrain.enteties.User;
import com.naverrain.menu.Menu;
import com.naverrain.services.UserManagementService;
import com.naverrain.services.impl.DefaultUserManagementService;

import java.util.Scanner;

public class SignInMenu implements Menu {

    private ApplicationContext context;
    private UserManagementService userManagementService;

    {
        context = ApplicationContext.getInstance();
        userManagementService = DefaultUserManagementService.getInstance();
    }

    @Override
    public void start() {
        printMenuHeader();
        Scanner sc = new Scanner(System.in);

        System.out.print("Please enter your email: ");
        String userEmail = sc.next();

        System.out.print("Please enter your password: ");
        String userPassword = sc.next();

        User user = userManagementService.getUserByEmail(userEmail);
        if (user != null || user.getPassword().equals(userPassword)){
            System.out.printf("Welcome back, %s %s! Enjoy shopping with us.",
                    user.getFirstName(), user.getLastName());
            System.out.println();
            context.setLoggedInUser(user);
        }
        else {
            System.out.println("Login failed. Please check your email and password.");
        }
        context.getMainMenu().start();
    }

    @Override
    public void printMenuHeader() {
        System.out.println("===== SIGN IN MENU =====");
    }
}
