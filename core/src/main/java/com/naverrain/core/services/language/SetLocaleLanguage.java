package com.naverrain.core.services.language;

import com.naverrain.core.configs.ApplicationContext;

import java.util.Locale;
import java.util.ResourceBundle;

public class SetLocaleLanguage {

    public static ResourceBundle updateResourceBundle(String resourceBundleName) {
        Locale currentLocale = ApplicationContext.getInstance().getCurrentLocale();
        return ResourceBundle.getBundle(resourceBundleName, currentLocale);
    }
}
