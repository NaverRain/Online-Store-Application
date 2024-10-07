package com.naverrain.core.menu.impl;

import com.naverrain.core.configs.ApplicationContext;
import com.naverrain.core.menu.Menu;
import com.naverrain.core.services.language.SetLocaleLanguage;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ChangeLanguageMenu implements Menu {

    private static final int ENGLISH_ID = 1;
    private static final int RUSSIAN_ID = 2;

    ResourceBundle rb;

    @Override
    public void start() {

        rb = SetLocaleLanguage.updateResourceBundle(RESOURCE_BUNDLE_NAME);

        printMenuHeader();

        Scanner sc = new Scanner(System.in);
        int languageId = sc.nextInt();

        Locale newLocale;

        switch (languageId){
            case ENGLISH_ID:
                newLocale = new Locale("en", "US");
                break;
            case RUSSIAN_ID:
                newLocale = new Locale("ru", "RU");
                break;
            default:
                newLocale = Locale.getDefault();
                break;
        }

        ApplicationContext.getInstance().setCurrentLocale(newLocale);

        rb = SetLocaleLanguage.updateResourceBundle(RESOURCE_BUNDLE_NAME);

        System.out.println(rb.getString(languageId == ENGLISH_ID
                ? "language.changed.en.msg"
                : "language.changed.ru.msg"));

        new MainMenu().start();
    }

    @Override
    public void printMenuHeader() {
        System.out.println(rb.getString("change.language.header"));
        System.out.println(rb.getString("select.language.cta"));
    }
}
