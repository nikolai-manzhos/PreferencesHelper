package com.defaultapps.preferenceshelperexample;

import com.defaultapps.preferenceshelper.DefaultPreferencesManager;

/**
 * Created on 5/5/2017.
 */

public class CustomPreferencesManager extends DefaultPreferencesManager {

    private static final String PROPERTY = "property";

    public String getStringProperty() {
        return getPreferencesHelper().getString(PROPERTY);
    }
}
