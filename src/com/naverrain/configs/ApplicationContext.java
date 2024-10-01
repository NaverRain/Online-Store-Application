package com.naverrain.configs;

import com.naverrain.enteties.Cart;
import com.naverrain.enteties.User;
import com.naverrain.enteties.impl.DefaultCart;
import com.naverrain.menu.Menu;

import java.util.Locale;

public class ApplicationContext {

    private static ApplicationContext instance;

    private Locale currentLocale = Locale.getDefault();

    private User loggedInUser;
    private Menu mainMenu;
    private Cart sessionCart;

    private ApplicationContext() {
    }

    public void setLoggedInUser(User user) {
        if (this.sessionCart != null) {
            this.sessionCart.clear();
        }
        this.loggedInUser = user;
    }

    public User getLoggedInUser() {
        return this.loggedInUser;
    }

    public void setMainMenu(Menu menu) {
        this.mainMenu = menu;
    }

    public Menu getMainMenu() {
        return this.mainMenu;
    }

    public static ApplicationContext getInstance() {
        if (instance == null) {
            instance = new ApplicationContext();
        }
        return instance;
    }

    public Cart getSessionCart() {
        if (this.sessionCart == null) {
            this.sessionCart = new DefaultCart();
        }
        return this.sessionCart;
    }

    public Locale getCurrentLocale() {
        return currentLocale;
    }

    public void setCurrentLocale(Locale locale) {
        this.currentLocale = locale;
    }
}
