package com.naverrain.core.menu.impl;

import com.naverrain.core.configs.ApplicationContext;
import com.naverrain.core.menu.Menu;
import com.naverrain.core.services.language.SetLocaleLanguage;

import java.util.ResourceBundle;
import java.util.Scanner;

public class ChangeEmailMenu implements Menu {

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
        String userInput = sc.next();

        context.getLoggedInUser().setEmail(userInput);
        System.out.println(rb.getString("mail.changed.msg"));
        new MainMenu().start();
    }

    @Override
    public void printMenuHeader() {
        System.out.println(rb.getString("change.email.header"));
        System.out.print(rb.getString("enter.new.email.cta"));
    }
}
