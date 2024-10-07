package com.naverrain.core.menu.impl;

import com.naverrain.core.configs.ApplicationContext;
import com.naverrain.core.menu.Menu;
import com.naverrain.core.services.impl.MySqlUserManagementService;
import com.naverrain.core.services.language.SetLocaleLanguage;
import com.naverrain.persistence.enteties.User;
import com.naverrain.core.services.UserManagementService;

import java.util.ResourceBundle;
import java.util.Scanner;

public class SignInMenu implements Menu {

    private ApplicationContext context;
    private UserManagementService userManagementService;
    private ResourceBundle rb;
    {
        context = ApplicationContext.getInstance();
        userManagementService = new MySqlUserManagementService();
    }

    @Override
    public void start() {
        rb = SetLocaleLanguage.updateResourceBundle(RESOURCE_BUNDLE_NAME);

        printMenuHeader();
        Scanner sc = new Scanner(System.in);

        System.out.print(rb.getString("please.enter.email"));
        String userEmail = sc.next();

        System.out.print(rb.getString("please.enter.password"));
        String userPassword = sc.next();

        User user = userManagementService.getUserByEmail(userEmail);
        if (user != null && user.getPassword().equals(userPassword)){
            System.out.printf(rb.getString("welcome.back.msg"),
                    user.getFirstName(), user.getLastName());
            System.out.println();
            context.setLoggedInUser(user);
        }
        else {
            System.out.println(rb.getString("failed.login.msg"));
        }
        context.getMainMenu().start();
    }

    @Override
    public void printMenuHeader() {
        System.out.println(rb.getString("sign.in.header"));
    }
}
