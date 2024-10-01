package com.naverrain.menu.impl;

import com.naverrain.configs.ApplicationContext;
import com.naverrain.enteties.User;
import com.naverrain.enteties.impl.DefaultUser;
import com.naverrain.menu.Menu;
import com.naverrain.services.UserManagementService;
import com.naverrain.services.impl.DefaultUserManagementService;
import com.naverrain.utis.language.SetLocaleLanguage;

import java.util.ResourceBundle;
import java.util.Scanner;

public class SignUpMenu implements Menu {

    private ApplicationContext context;
    private UserManagementService userManagementService;
    private ResourceBundle rb;
    {
        context = ApplicationContext.getInstance();
        userManagementService = DefaultUserManagementService.getInstance();
    }


    @Override
    public void start() {
        rb = SetLocaleLanguage.updateResourceBundle(RESOURCE_BUNDLE_NAME);

        printMenuHeader();
        Scanner sc = new Scanner(System.in);
        System.out.print(rb.getString("enter.first.name"));
        String userFirstName = sc.next();

        System.out.print(rb.getString("enter.last.name"));
        String userLastName = sc.next();

        System.out.print(rb.getString("enter.password"));
        String userPassword = sc.next();

        System.out.print(rb.getString("enter.email"));

        sc = new Scanner(System.in);
        String userEmail = sc.nextLine();

        userManagementService.getUsers();
        User user = new DefaultUser(userFirstName, userLastName, userPassword, userEmail);

        String errorMessage = userManagementService.registerUser(user);
        if (errorMessage == null || errorMessage.isEmpty()){
            context.setLoggedInUser(user);
            System.out.println(rb.getString("registration.success.msg"));
        }
        else {
            System.out.println(rb.getString("registration.fail.msg"));
            System.out.println(errorMessage);
        }
        context.getMainMenu().start();
    }

    @Override
    public void printMenuHeader() {
        System.out.println(rb.getString("sign.up.header"));

    }
}
