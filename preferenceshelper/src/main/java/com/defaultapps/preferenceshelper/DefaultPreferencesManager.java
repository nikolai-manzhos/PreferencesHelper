package com.defaultapps.preferenceshelper;


public class DefaultPreferencesManager extends PreferencesManager {

    private static final String FIRST_TIME_USER = "first_time_user";

    public boolean getFirstTimeUser() {
        return getPreferencesHelper().getBoolean(FIRST_TIME_USER, true);
    }

    public void setFirstTimeUser(boolean value) {
        getPreferencesHelper().putBoolean(FIRST_TIME_USER, value);
    }
}
