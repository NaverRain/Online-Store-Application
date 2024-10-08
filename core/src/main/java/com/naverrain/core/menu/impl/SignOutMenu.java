package com.naverrain.core.menu.impl;

import com.naverrain.core.configs.ApplicationContext;
import com.naverrain.core.menu.Menu;
import com.naverrain.core.services.language.SetLocaleLanguage;

import java.util.ResourceBundle;

public class SignOutMenu implements Menu {

    private ApplicationContext context;
    private ResourceBundle rb;
    {
        context = ApplicationContext.getInstance();
    }

    @Override
    public void start() {
        rb = SetLocaleLanguage.updateResourceBundle(RESOURCE_BUNDLE_NAME);

        printMenuHeader();
        context.setLoggedInUser(null);
        context.getMainMenu().start();
    }

    @Override
    public void printMenuHeader() {
        System.out.println(rb.getString("sign.out.header"));
        System.out.println(rb.getString("sign.out.msg"));
    }
}
