package com.naverrain.utis.language;

import com.naverrain.configs.ApplicationContext;

import java.util.Locale;
import java.util.ResourceBundle;

public class SetLocaleLanguage {

    public static ResourceBundle updateResourceBundle(String resourceBundleName) {
        Locale currentLocale = ApplicationContext.getInstance().getCurrentLocale();
        return ResourceBundle.getBundle(resourceBundleName, currentLocale);
    }
}
