package com.naverrain.core.menu.impl;

import com.naverrain.core.configs.ApplicationContext;
import com.naverrain.core.menu.Menu;
import com.naverrain.core.Main;
import com.naverrain.core.services.language.SetLocaleLanguage;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class MainMenu implements Menu {


    public static final String MENU_COMMAND = "menu";

    private ResourceBundle rb;
    private ApplicationContext context;
    {
        context = ApplicationContext.getInstance();
        Locale currentLocale = context.getCurrentLocale();
    }

    @Override
    public void start() {
        rb = SetLocaleLanguage.updateResourceBundle(RESOURCE_BUNDLE_NAME);

        if (context.getMainMenu() == null) {
            context.setMainMenu(this);
        }

        Menu menuToNavigate = null;
        mainLoop: while (true) {
            printMenuHeader();

            Scanner sc = new Scanner(System.in);

            System.out.print(rb.getString("user.input"));
            String userInput = sc.next();
            if (userInput.equalsIgnoreCase(Main.EXIT_COMMAND)) {
                System.exit(0);
            } else {
                int commandNumber = Integer.parseInt(userInput);
                switch (commandNumber) {

                    case 1:
                        menuToNavigate = new SignUpMenu();
                        break mainLoop;
                    case 2:
                        if (context.getLoggedInUser() == null) {
                            menuToNavigate = new SignInMenu();
                        } else {
                            menuToNavigate = new SignOutMenu();
                        }
                        break mainLoop;
                    case 3:
                        menuToNavigate = new ProductCatalogMenu();
                        break mainLoop;
                    case 4:
                        menuToNavigate = new MyOrdersMenu();
                        break mainLoop;
                    case 5:
                        menuToNavigate = new SettingsMenu();
                        break mainLoop;
                    case 6:
                        menuToNavigate = new CustomerListMenu();
                        break mainLoop;
                    case 7:

                        menuToNavigate = new ResetPasswordMenu();
                        break mainLoop;
                    case 8:
                        menuToNavigate = new ChangeLanguageMenu();
                        break mainLoop;
                    default:
                        System.out.println(rb.getString("main.menu.invalid.input.msg"));
                        continue;
                }
            }
        }

        menuToNavigate.start();

    }

    @Override
    public void printMenuHeader() {
        Locale currentLocale = ApplicationContext.getInstance().getCurrentLocale();
        rb = ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME, currentLocale);

        System.out.println(rb.getString("main.menu.header"));
        if (context.getLoggedInUser() == null){
            System.out.println(rb.getString("main.menu.text.for.logged.out.user"));
        }
        else
            System.out.println(rb.getString("main.menu.text.for.logged.in.user"));
    }
}
