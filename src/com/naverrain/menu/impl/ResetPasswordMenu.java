package com.naverrain.menu.impl;

import com.naverrain.enteties.User;
import com.naverrain.menu.Menu;
import com.naverrain.services.ResetPasswordService;
import com.naverrain.services.UserManagementService;
import com.naverrain.utis.language.SetLocaleLanguage;

import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

public class ResetPasswordMenu implements Menu {

    private ResetPasswordService resetPasswordService;
    private UserManagementService userManagementService;
    private ResourceBundle rb;

    @Override
    public void start() {
        rb = SetLocaleLanguage.updateResourceBundle(RESOURCE_BUNDLE_NAME);

        printMenuHeader();

        Scanner sc = new Scanner(System.in);
        String userInput = sc.next();

        System.out.println(rb.getString("password.send.to.email.msg"));
        CompletableFuture.runAsync(() -> {
            User user = userManagementService.getUserByEmail(userInput);
            resetPasswordService.resetPassword(user);
        });

        new MainMenu().start();
    }

    @Override
    public void printMenuHeader() {
        System.out.println(rb.getString("reset.password.header"));
        System.out.println(rb.getString("enter.your.email.msg"));
    }
}
