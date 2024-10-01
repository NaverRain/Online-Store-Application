package com.naverrain.menu.impl;

import com.naverrain.configs.ApplicationContext;
import com.naverrain.enteties.User;
import com.naverrain.enteties.impl.DefaultUser;
import com.naverrain.menu.Menu;
import com.naverrain.services.UserManagementService;
import com.naverrain.services.impl.DefaultUserManagementService;

import java.util.Scanner;

public class SignUpMenu implements Menu {

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
        System.out.print("Please enter your first name: ");
        String userFirstName = sc.next();

        System.out.print("Please enter your last name: ");
        String userLastName = sc.next();

        System.out.print("Please enter your password: ");
        String userPassword = sc.next();

        System.out.print("Please enter your email: ");

        sc = new Scanner(System.in);
        String userEmail = sc.nextLine();

        userManagementService.getUsers();
        User user = new DefaultUser(userFirstName, userLastName, userPassword, userEmail);

        String errorMessage = userManagementService.registerUser(user);
        if (errorMessage == null || errorMessage.isEmpty()){
            context.setLoggedInUser(user);
            System.out.println("Registration successful! Welcome to our community.");
        }
        else {
            System.out.println("Registration failed. Please try again.");
            System.out.println(errorMessage);
        }
        context.getMainMenu().start();
    }

    @Override
    public void printMenuHeader() {
        System.out.println("===== SIGN UP MENU =====");

    }
}
