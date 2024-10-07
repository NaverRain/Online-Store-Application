package com.naverrain.core.menu.impl;

import com.naverrain.core.configs.ApplicationContext;
import com.naverrain.persistence.enteties.Cart;
import com.naverrain.persistence.enteties.Product;
import com.naverrain.core.menu.Menu;
import com.naverrain.core.services.ProductManagementService;
import com.naverrain.core.services.impl.MySqlProductManagementService;
import com.naverrain.core.services.language.SetLocaleLanguage;

import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ProductCatalogMenu implements Menu {

    private static final String CHECKOUT_COMMAND = "checkout";
    private ApplicationContext context;
    private ProductManagementService productManagementService;
    private ResourceBundle rb;
    {
        context = ApplicationContext.getInstance();
        productManagementService = new MySqlProductManagementService();
    }

    @Override
    public void start() {
        rb = SetLocaleLanguage.updateResourceBundle(RESOURCE_BUNDLE_NAME);

        Menu menuToNavigate = null;
        while (true) {
            printMenuHeader();
            printProductsToConsole();

            String userInput = readUserInput();

            if (context.getLoggedInUser() == null) {
                menuToNavigate = new MainMenu();
                System.out.println(rb.getString("not.logged.in.msg"));
                break;
            }

            if (userInput.equalsIgnoreCase(MainMenu.MENU_COMMAND)) {
                menuToNavigate = new MainMenu();
                break;
            }

            if (userInput.equalsIgnoreCase(CHECKOUT_COMMAND)) {
                Cart sessionCart = context.getSessionCart();
                if (sessionCart == null || sessionCart.isEmpty()) {
                    System.out.println(rb.getString("empty.cart.msg"));
                } else {
                    menuToNavigate = new CheckoutMenu();
                    break;
                }
            } else {
                Product productToAddToCart = fetchProduct(userInput);

                if (productToAddToCart == null) {
                    System.out.println(rb.getString("enter.product.id.cta"));
                    continue;
                }

                processAddToCart(productToAddToCart);
            }
        }

        menuToNavigate.start();
    }

    private String readUserInput() {
        System.out.println(rb.getString("proceed.to.checkout.cta"));
        Scanner sc = new Scanner(System.in);
        String userInput = sc.next();
        return userInput;
    }

    private void printProductsToConsole() {
        List<Product> products = productManagementService.getProducts();
        if (products != null) {
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }

    private Product fetchProduct(String userInput) {
        int productIdToAddToCart = Integer.parseInt(userInput);
        Product productToAddToCart = productManagementService.getProductById(productIdToAddToCart);
        return productToAddToCart;
    }

    private void processAddToCart(Product productToAddToCart) {
        context.getSessionCart().addProduct(productToAddToCart);
        System.out.printf(rb.getString("added.product.msg"), productToAddToCart.getProductName());

    }

    @Override
    public void printMenuHeader() {
        System.out.println(rb.getString("product.catalog.header"));
        System.out.println(rb.getString("catalog.cta"));
    }
}
