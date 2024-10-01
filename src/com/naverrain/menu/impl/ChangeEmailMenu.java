package com.naverrain.menu.impl;

import com.naverrain.configs.ApplicationContext;
import com.naverrain.menu.Menu;

import java.util.Scanner;

public class ChangeEmailMenu implements Menu {

    private ApplicationContext context;
    {
        context = ApplicationContext.getInstance();
    }

    @Override
    public void start() {
        printMenuHeader();
        Scanner sc = new Scanner(System.in);
        String userInput = sc.next();

        context.getLoggedInUser().setEmail(userInput);
        System.out.println("Your email has been successfully updated!");
        new MainMenu().start();
    }

    @Override
    public void printMenuHeader() {
        System.out.println("===== CHANGE EMAIL MENU =====");
        System.out.print("Enter a new email: ");
    }
}
