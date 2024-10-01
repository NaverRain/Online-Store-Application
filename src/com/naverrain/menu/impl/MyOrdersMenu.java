package com.naverrain.menu.impl;

import com.naverrain.configs.ApplicationContext;
import com.naverrain.enteties.Order;
import com.naverrain.menu.Menu;
import com.naverrain.services.OrderManagementService;
import com.naverrain.services.impl.DefaultOrderManagementService;
import com.naverrain.utis.language.SetLocaleLanguage;

import java.util.List;
import java.util.ResourceBundle;

public class MyOrdersMenu implements Menu {

    private ApplicationContext context;
    private OrderManagementService orderManagementService;
    private ResourceBundle rb;
    {
        context = ApplicationContext.getInstance();
        orderManagementService = DefaultOrderManagementService.getInstance();
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
        List<Order> loggedInUserOrders = orderManagementService
                .getOrdersByUserId(context.getLoggedInUser().getId());
        if (loggedInUserOrders == null || loggedInUserOrders.size() == 0) {
            System.out.println(rb.getString("no.orders.msg"));
        } else {
            for (Order order : loggedInUserOrders) {
                System.out.println(order);
            }
        }
    }

    @Override
    public void printMenuHeader() {
        System.out.println(rb.getString("my.orders.header"));
    }
}
