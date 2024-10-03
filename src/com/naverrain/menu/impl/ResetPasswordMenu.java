package com.naverrain.menu.impl;

import com.naverrain.enteties.User;
import com.naverrain.menu.Menu;
import com.naverrain.services.UserManagementService;
import com.naverrain.services.impl.DefaultUserManagementService;
import com.naverrain.utis.language.SetLocaleLanguage;

import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

public class ResetPasswordMenu implements Menu {

    private UserManagementService userManagementService;
    private ResourceBundle rb;

    {
        userManagementService = DefaultUserManagementService.getInstance();
    }

    @Override
    public void start() {
        rb = SetLocaleLanguage.updateResourceBundle(RESOURCE_BUNDLE_NAME);

        printMenuHeader();

        Scanner sc = new Scanner(System.in);
        String userInput = sc.next();

        System.out.println(rb.getString("password.send.to.email.msg"));
        CompletableFuture.runAsync(() -> {
            User user = userManagementService.getUserByEmail(userInput);
            userManagementService.resetPasswordForUser(user);
        });

        new MainMenu().start();
    }

    @Override
    public void printMenuHeader() {
        System.out.println(rb.getString("reset.password.header"));
        System.out.println(rb.getString("enter.your.email.msg"));
    }
}
