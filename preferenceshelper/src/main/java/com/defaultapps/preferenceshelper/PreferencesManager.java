package com.defaultapps.preferenceshelper;

import android.support.annotation.VisibleForTesting;

@SuppressWarnings("WeakerAccess")
public class PreferencesManager {
    private PreferencesHelper preferencesHelper;

    PreferencesManager() {
        preferencesHelper = PreferencesHelper.getInstance();
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    public PreferencesHelper getPreferencesHelper() {
        return preferencesHelper;
    }
}
