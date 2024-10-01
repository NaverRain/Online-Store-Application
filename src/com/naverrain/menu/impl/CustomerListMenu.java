package com.naverrain.menu.impl;

import com.naverrain.configs.ApplicationContext;
import com.naverrain.enteties.User;
import com.naverrain.menu.Menu;
import com.naverrain.services.UserManagementService;
import com.naverrain.services.impl.DefaultUserManagementService;
import com.naverrain.utis.language.SetLocaleLanguage;

import java.util.List;
import java.util.ResourceBundle;

public class CustomerListMenu implements Menu {

    private ApplicationContext context;
    private UserManagementService userManagementService;
    private ResourceBundle rb;
    {
        userManagementService = DefaultUserManagementService.getInstance();
        context = ApplicationContext.getInstance();
    }

    @Override
    public void start() {
        rb = SetLocaleLanguage.updateResourceBundle(RESOURCE_BUNDLE_NAME);

        printMenuHeader();
        List<User> users = userManagementService.getUsers();

        if (users == null || users.size() == 0) {
            System.out.println(rb.getString("no.users.msg"));
        } else {
            for (User user : users) {
                System.out.println(user);
            }
        }
        context.getMainMenu().start();
    }

    @Override
    public void printMenuHeader() {
        System.out.println(rb.getString("customer.list.header"));
    }
}
