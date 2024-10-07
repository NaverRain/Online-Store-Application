package com.naverrain.core;

import com.naverrain.core.menu.Menu;
import com.naverrain.core.menu.impl.MainMenu;

public class Main {
    public static final String EXIT_COMMAND = "exit";

    public static void main(String[] args) {
        Menu mainMenu = new MainMenu();
        mainMenu.start();
    }
}
