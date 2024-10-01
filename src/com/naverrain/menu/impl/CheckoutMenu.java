package com.naverrain.menu.impl;

import com.naverrain.configs.ApplicationContext;
import com.naverrain.enteties.Order;
import com.naverrain.enteties.impl.DefaultOrder;
import com.naverrain.menu.Menu;
import com.naverrain.services.OrderManagementService;
import com.naverrain.services.impl.DefaultOrderManagementService;
import com.naverrain.utis.language.SetLocaleLanguage;

import java.util.ResourceBundle;
import java.util.Scanner;

public class CheckoutMenu implements Menu {

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

        System.out.println(rb.getString("thank.you.msg"));
        new MainMenu().start();
    }

    private boolean createOrder(String creditCardNumber) {
        if (context.getSessionCart().getProducts().isEmpty()) {
            System.out.println(rb.getString("no.products.msg"));
            return false;
        }

        Order order = new DefaultOrder();
        if (!order.isCreditCardNumberValid(creditCardNumber)) {
            System.out.println(rb.getString("invalid.credit.card.number.msg"));
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
        System.out.println(rb.getString("checkout.menu.header"));
        System.out.println(rb.getString("enter.credit.card.number.cta"));
    }
}
