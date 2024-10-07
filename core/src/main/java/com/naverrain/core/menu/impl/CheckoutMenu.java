package com.naverrain.core.menu.impl;


import com.naverrain.core.configs.ApplicationContext;
import com.naverrain.core.menu.Menu;
import com.naverrain.core.services.language.SetLocaleLanguage;

import com.naverrain.persistence.enteties.Purchase;
import com.naverrain.persistence.enteties.impl.DefaultPurchase;
import com.naverrain.core.services.PurchaseManagementService;
import com.naverrain.core.services.impl.MySqlPurchaseManagementService;

import java.util.ResourceBundle;
import java.util.Scanner;

public class CheckoutMenu implements Menu {

    private ApplicationContext context;
    private PurchaseManagementService purchaseManagementService;
    private ResourceBundle rb;
    {
        context = ApplicationContext.getInstance();
        purchaseManagementService = new MySqlPurchaseManagementService();
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

        Purchase purchase = new DefaultPurchase();
        if (!purchase.isCreditCardNumberValid(creditCardNumber)) {
            System.out.println(rb.getString("invalid.credit.card.number.msg"));
            return false;
        }

        purchase.setCreditCardNumber(creditCardNumber);
        purchase.setProducts(context.getSessionCart().getProducts());
        purchase.setCustomerId(context.getLoggedInUser().getId());
        purchaseManagementService.addPurchase(purchase);
        return true;
    }

    @Override
    public void printMenuHeader() {
        System.out.println(rb.getString("checkout.menu.header"));
        System.out.println(rb.getString("enter.credit.card.number.cta"));
    }
}
