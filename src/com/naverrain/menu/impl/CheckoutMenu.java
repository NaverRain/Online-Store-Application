package com.naverrain.menu.impl;

import com.naverrain.configs.ApplicationContext;
import com.naverrain.enteties.Order;
import com.naverrain.enteties.impl.DefaultOrder;
import com.naverrain.menu.Menu;
import com.naverrain.services.OrderManagementService;
import com.naverrain.services.impl.DefaultOrderManagementService;

import java.util.Scanner;

public class CheckoutMenu implements Menu {

    private ApplicationContext context;
    private OrderManagementService orderManagementService;
    {
        context = ApplicationContext.getInstance();
        orderManagementService = DefaultOrderManagementService.getInstance();
    }



    @Override
    public void start() {
        while (true) {
            printMenuHeader();
            Scanner sc = new Scanner(System.in);
            String userInput = sc.next();

            if (!createOrder(userInput)) {
                continue;
            }
            context.getSessionCart().clear();
            break;
        }

        System.out.println("Thank you for your purchase! A confirmation email with delivery details has been sent to you.");
        new MainMenu().start();
    }

    private boolean createOrder(String creditCardNumber) {
        if (context.getSessionCart().getProducts().isEmpty()) {
            System.out.println("No products in the cart. Please add products before proceeding to checkout.");
            return false;
        }

        Order order = new DefaultOrder();
        if (!order.isCreditCardNumberValid(creditCardNumber)) {
            System.out.println("Invalid credit card number. Please check and try again.");
            return false;
        }

        order.setCreditCardNumber(creditCardNumber);
        order.setProducts(context.getSessionCart().getProducts());
        order.setCustomerId(context.getLoggedInUser().getId());
        orderManagementService.addOrder(order);
        return true;
    }

    @Override
    public void printMenuHeader() {
        System.out.println("===== CHECKOUT MENU =====");
        System.out.println("Enter your credit card number " +
                "and press enter to confirm purchase.");
    }
}
