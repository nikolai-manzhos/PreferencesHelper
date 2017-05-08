package com.defaultapps.preferenceshelper;


public class PreferencesManager {
    private PreferencesHelper preferencesHelper;

    protected PreferencesManager() {
        preferencesHelper = PreferencesHelper.getInstance();
    }

    protected PreferencesHelper getPreferencesHelper() {
        return preferencesHelper;
    }
}
