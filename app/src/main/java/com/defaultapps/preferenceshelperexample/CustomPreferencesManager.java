package com.defaultapps.preferenceshelperexample;

import com.defaultapps.preferenceshelper.DefaultPreferencesManager;


public class CustomPreferencesManager extends DefaultPreferencesManager {

    private static final String PROPERTY = "property";

    public String getStringProperty() {
        return getPreferencesHelper().getString(PROPERTY);
    }
}
