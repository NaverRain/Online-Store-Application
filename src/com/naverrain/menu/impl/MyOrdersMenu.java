package com.naverrain.menu.impl;

import com.naverrain.configs.ApplicationContext;
import com.naverrain.enteties.Order;
import com.naverrain.menu.Menu;
import com.naverrain.services.OrderManagementService;
import com.naverrain.services.impl.DefaultOrderManagementService;

import java.util.List;

public class MyOrdersMenu implements Menu {

    private ApplicationContext context;
    private OrderManagementService orderManagementService;

    {
        context = ApplicationContext.getInstance();
        orderManagementService = DefaultOrderManagementService.getInstance();
    }


    @Override
    public void start() {
        printMenuHeader();
        if (context.getLoggedInUser() == null){
            System.out.println("Please log in or create a new account to view your orders.");
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
            System.out.println(
                    "Unfortunately, you don't have any orders yet. " +
                            "Please navigate back to the main menu to place a new order.");
        } else {
            for (Order order : loggedInUserOrders) {
                System.out.println(order);
            }
        }
    }

    @Override
    public void printMenuHeader() {
        System.out.println("===== MY ORDERS MENU =====");
    }
}
