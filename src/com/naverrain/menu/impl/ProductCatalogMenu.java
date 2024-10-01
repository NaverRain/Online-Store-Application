package com.naverrain.menu.impl;

import com.naverrain.configs.ApplicationContext;
import com.naverrain.enteties.Cart;
import com.naverrain.enteties.Product;
import com.naverrain.menu.Menu;
import com.naverrain.services.ProductManagementService;
import com.naverrain.services.impl.DefaultProductManagementService;

import java.util.List;
import java.util.Scanner;

public class ProductCatalogMenu implements Menu {

    private static final String CHECKOUT_COMMAND = "checkout";
    private ApplicationContext context;
    private ProductManagementService productManagementService;

    {
        context = ApplicationContext.getInstance();
        productManagementService = DefaultProductManagementService.getInstance();
    }

    @Override
    public void start() {
        Menu menuToNavigate = null;
        while (true) {
            printMenuHeader();
            printProductsToConsole();

            String userInput = readUserInput();

            if (context.getLoggedInUser() == null) {
                menuToNavigate = new MainMenu();
                System.out.println("You are not logged in. Please sign in or create a new account to proceed.");
                break;
            }

            if (userInput.equalsIgnoreCase(MainMenu.MENU_COMMAND)) {
                menuToNavigate = new MainMenu();
                break;
            }

            if (userInput.equalsIgnoreCase(CHECKOUT_COMMAND)) {
                Cart sessionCart = context.getSessionCart();
                if (sessionCart == null || sessionCart.isEmpty()) {
                    System.out.println("Your cart is empty. Please add a product to the cart before proceeding to checkout.");
                } else {
                    menuToNavigate = new CheckoutMenu();
                    break;
                }
            } else {
                Product productToAddToCart = fetchProduct(userInput);

                if (productToAddToCart == null) {
                    System.out.println("Please enter a valid product ID to add to the cart or 'checkout' to proceed with checkout.");
                    continue;
                }

                processAddToCart(productToAddToCart);
            }
        }

        menuToNavigate.start();
    }

    private String readUserInput() {
        System.out.println("Please enter a valid product ID to add to the cart or 'checkout' to proceed with checkout.");
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
        System.out.printf("Product %s has been added to your cart. " +
                "Enter the product ID to add another product," +
                " or 'checkout' to proceed to checkout.%n", productToAddToCart.getProductName());

    }

    @Override
    public void printMenuHeader() {
        System.out.println("===== PRODUCT CATALOG =====");
        System.out.println("Enter a product ID to add it to the cart or 'menu' to navigate back to the main menu.");
    }
}
