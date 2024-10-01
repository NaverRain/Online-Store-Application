package com.naverrain;

import com.naverrain.menu.Menu;
import com.naverrain.menu.impl.MainMenu;

public class Main {
    public static final String EXIT_COMMAND = "exit";

    public static void main(String[] args) {
        Menu mainMenu = new MainMenu();
        mainMenu.start();
    }
}
