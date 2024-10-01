package com.naverrain.menu.impl;

import com.naverrain.configs.ApplicationContext;
import com.naverrain.menu.Menu;
import com.naverrain.utis.language.SetLocaleLanguage;

import java.util.ResourceBundle;
import java.util.Scanner;

public class SettingsMenu implements Menu {

    private ApplicationContext context;
    private ResourceBundle rb;
    {
        context = ApplicationContext.getInstance();
    }

    @Override
    public void start() {
        rb = SetLocaleLanguage.updateResourceBundle(RESOURCE_BUNDLE_NAME);

        Menu menu = null;
        mainLoop: while (true){
            printMenuHeader();
            if (context.getLoggedInUser() == null){
                System.out.println(rb.getString("settings.log.in.msg"));
                new MainMenu().start();
                return;
            }
            else {
                System.out.println(rb.getString("settings.options"));
                System.out.println(rb.getString("enter.option.msg"));
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
                        System.out.println(rb.getString("settings.invalid.input.msg"));
                        continue;
                }
            }
        }
        menu.start();
    }



    @Override
    public void printMenuHeader() {
        System.out.println(rb.getString("settings.header"));
    }
}
