package com.naverrain.core.menu.impl;

import com.naverrain.core.configs.ApplicationContext;
import com.naverrain.core.menu.Menu;
import com.naverrain.core.services.language.SetLocaleLanguage;

import java.util.ResourceBundle;
import java.util.Scanner;

public class ChangePasswordMenu implements Menu {

    private ResourceBundle rb;
    private ApplicationContext context;
    {
        context = ApplicationContext.getInstance();
    }

    @Override
    public void start() {
        rb = SetLocaleLanguage.updateResourceBundle(RESOURCE_BUNDLE_NAME);

        printMenuHeader();
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        context.getLoggedInUser().setPassword(userInput);
        System.out.println(rb.getString("password.changed.msg"));

        new MainMenu().start();
    }

    @Override
    public void printMenuHeader() {
        System.out.println(rb.getString("change.password.header"));
        System.out.print(rb.getString("enter.new.password.cta"));
    }
}
