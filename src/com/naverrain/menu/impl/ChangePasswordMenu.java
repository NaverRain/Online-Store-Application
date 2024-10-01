package com.naverrain.menu.impl;

import com.naverrain.configs.ApplicationContext;
import com.naverrain.menu.Menu;

import java.util.Scanner;

public class ChangePasswordMenu implements Menu {

    private ApplicationContext context;
    {
        context = ApplicationContext.getInstance();
    }

    @Override
    public void start() {
        printMenuHeader();
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        context.getLoggedInUser().setPassword(userInput);
        System.out.println("Your password has been changed successfully!");

        new MainMenu().start();
    }

    @Override
    public void printMenuHeader() {
        System.out.println("===== CHANGE PASSWORD MENU =====");
        System.out.print("Enter a new password: ");
    }
}
