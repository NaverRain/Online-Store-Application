package com.naverrain.menu.impl;

import com.naverrain.configs.ApplicationContext;
import com.naverrain.enteties.Purchase;
import com.naverrain.menu.Menu;
import com.naverrain.services.PurchaseManagementService;
import com.naverrain.services.impl.DefaultPurchaseManagementService;
import com.naverrain.utis.language.SetLocaleLanguage;

import java.util.List;
import java.util.ResourceBundle;

public class MyOrdersMenu implements Menu {

    private ApplicationContext context;
    private PurchaseManagementService purchaseManagementService;
    private ResourceBundle rb;
    {
        context = ApplicationContext.getInstance();
        purchaseManagementService = DefaultPurchaseManagementService.getInstance();
    }


    @Override
    public void start() {
        rb = SetLocaleLanguage.updateResourceBundle(RESOURCE_BUNDLE_NAME);
        printMenuHeader();
        if (context.getLoggedInUser() == null){
            System.out.println(rb.getString("log.in.msg"));
            new MainMenu().start();
            return;
        }
        else {
            printOrdersToConsole();
        }
        new MainMenu().start();
    }

    private void printOrdersToConsole(){
        List<Purchase> loggedInUserPurchases = purchaseManagementService
                .getPurchasesByUserId(context.getLoggedInUser().getId());
        if (loggedInUserPurchases == null || loggedInUserPurchases.size() == 0) {
            System.out.println(rb.getString("no.orders.msg"));
        } else {
            for (Purchase purchase : loggedInUserPurchases) {
                System.out.println(purchase);
            }
        }
    }

    @Override
    public void printMenuHeader() {
        System.out.println(rb.getString("my.orders.header"));
    }
}
