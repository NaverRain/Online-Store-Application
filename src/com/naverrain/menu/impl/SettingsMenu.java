package com.naverrain.menu.impl;

import com.naverrain.configs.ApplicationContext;
import com.naverrain.menu.Menu;

import java.util.Scanner;

public class SettingsMenu implements Menu {

    private static final String SETTINGS = "1. Change Password"
                                            + System.lineSeparator()
                                            + "2. Change Email";

    private ApplicationContext context;

    {
        context = ApplicationContext.getInstance();
    }

    @Override
    public void start() {
        Menu menu = null;
        mainLoop: while (true){
            printMenuHeader();
            if (context.getLoggedInUser() == null){
                System.out.println("Please log in or create a new account to modify your settings.");
                new MainMenu().start();
                return;
            }
            else {
                System.out.println(SETTINGS);
                System.out.println("Please enter your option, or type \"Menu\" to navigate back to the main menu.");
                Scanner sc = new Scanner(System.in);
                String userInput = sc.next();

                if (userInput.equalsIgnoreCase(MainMenu.MENU_COMMAND)){
                    menu = new MainMenu();
                    break mainLoop;
                }
                int userOption = Integer.parseInt(userInput);

                switch (userOption){
                    case 1:
                        menu = new ChangePasswordMenu();
                        break mainLoop;
                    case 2:
                        menu = new ChangeEmailMenu();
                        break mainLoop;
                    default:
                        System.out.println("Invalid input. Please choose option 1 or 2.");
                        continue;
                }
            }
        }
        menu.start();
    }



    @Override
    public void printMenuHeader() {
        System.out.println("===== SETTINGS MENU =====");
    }
}
