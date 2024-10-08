package com.naverrain.core.menu.impl;

import com.naverrain.core.configs.ApplicationContext;
import com.naverrain.core.menu.Menu;
import com.naverrain.core.services.impl.MySqlUserManagementService;
import com.naverrain.core.services.language.SetLocaleLanguage;
import com.naverrain.persistence.enteties.User;
import com.naverrain.persistence.enteties.impl.DefaultUser;
import com.naverrain.core.services.UserManagementService;

import java.util.ResourceBundle;
import java.util.Scanner;

public class SignUpMenu implements Menu {

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
        System.out.print(rb.getString("enter.first.name"));
        String userFirstName = sc.next();

        System.out.print(rb.getString("enter.last.name"));
        String userLastName = sc.next();

        System.out.print(rb.getString("enter.password"));
        String userPassword = sc.next();

        System.out.println("Password entered: " + userPassword);

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
