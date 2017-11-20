package com.defaultapps.preferenceshelper;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SuppressWarnings({"unused", "WeakerAccess"})
public class PreferencesHelper {

    private SharedPreferences sharedPreferences;

    static PreferencesHelper instance;

    private static String NULL_STRING = "null";
    private static String EMPTY_STRING = "empty";

    private PreferencesHelper(Builder builder) {
        if (builder.name == null && builder.mode == -1) {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(builder.context);
        } else {
            sharedPreferences = builder.context.getSharedPreferences(builder.name, builder.mode);
        }
    }

    public static PreferencesHelper.Builder builder(@NonNull Context context) {
        if (!(context instanceof Application)) throw new IllegalArgumentException("Provide application context");
        return new PreferencesHelper.Builder(context);
    }

    private static void createInstance(Builder builder) {
        instance = new PreferencesHelper(builder);
    }

    static PreferencesHelper getInstance() {
        if (instance == null) {
            throw new RuntimeException("Init PreferenceHelper on Application class in onCreate() method.");
        }
        return instance;
    }

    public int getInt(String key) {
        return sharedPreferences.getInt(key, 0);
    }

    public int getInt(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

    public void putInt(String key, int value) {
        SharedPreferences.Editor e = sharedPreferences.edit();
        e.putInt(key, value).apply();
    }

    public String getString(String key) {
        return sharedPreferences.getString(key, "");
    }

    public String getString(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    public void putString(String key, String value) {
        SharedPreferences.Editor e = sharedPreferences.edit();
        e.putString(key, value).apply();
    }

    public boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    public void putBoolean(String key, boolean value) {
        SharedPreferences.Editor e = sharedPreferences.edit();
        e.putBoolean(key, value).apply();
    }

    public long getLong(String key) {
        return sharedPreferences.getLong(key, 0);
    }

    public long getLong(String key, long defaultValue) {
        return sharedPreferences.getLong(key, defaultValue);
    }

    public void putLong(String key, long value) {
        SharedPreferences.Editor e = sharedPreferences.edit();
        e.putLong(key, value).apply();
    }

    public Set<String> getStringSet(String key) {
        return sharedPreferences.getStringSet(key, new HashSet<String>());
    }

    public Set<String> getStringSet(String key, Set<String> defaultValue) {
        return sharedPreferences.getStringSet(key, defaultValue);
    }

    public void putStringSet(String key, Set<String> value) {
        SharedPreferences.Editor e = sharedPreferences.edit();
        e.putStringSet(key, value).apply();
    }

    public void putStringList(String key, List<String> value) {
        StringBuilder sb = new StringBuilder();
        int size = value.size();

        for (int i=0; i < size-1; i++) {
            String stringValue = value.get(i);
            byte[] bytes = convertStringToBytesArray(stringValue);
            sb.append(convertBytesToHex(bytes)).append(";");
        }
        byte[] bytes = convertStringToBytesArray(value.get(size - 1));
        sb.append(convertBytesToHex(bytes));
        putString(key, sb.toString());
    }

    public List<String> getStringList(String key, List<String> defaultValue) {
        String listString = getString(key, null);
        if (listString == null) return defaultValue;

        final List<String> resultList = new ArrayList<>();
        String[] hexStrings = listString.split(";");
        for (String s: hexStrings) {
            int len = s.length();
            byte[] data = new byte[len/2];
            for (int i=0; i < len; i+=2) {
                data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                        + Character.digit(s.charAt(i+1), 16));
            }
            String resultString = new String(data);
            if (resultString.equals(NULL_STRING)) resultString = null;
            else if (resultString.equals(EMPTY_STRING)) resultString = "";
            resultList.add(resultString);
        }
        return resultList;
    }

    public List<String> getStringList(String key) {
        return getStringList(key, new ArrayList<String>());
    }

    String convertBytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (Byte item: bytes) {
            sb.append(String.format("%02x", item));
        }
        return sb.toString();
    }

    byte[] convertStringToBytesArray(String value) {
        byte[] bytes;
        if (value == null) {
            bytes = NULL_STRING.getBytes();
        } else if (value.equals("")) {
            bytes = EMPTY_STRING.getBytes();
        } else {
            bytes = value.getBytes();
        }
        return bytes;
    }

    public static class Builder {

        private Context context;
        private String name;
        private int mode = -1;

        private Builder(@NonNull Context context) {
            this.context = context;
        }

        public Builder setName(@NonNull String name) {
            this.name = name;
            return this;
        }

        @SuppressLint({"WorldReadableFiles", "WorldWriteableFiles"})
        @SuppressWarnings("deprecation")
        public Builder setMode(int mode) {
            if (mode == ContextWrapper.MODE_PRIVATE
                    || mode == ContextWrapper.MODE_WORLD_READABLE
                    || mode == ContextWrapper.MODE_WORLD_WRITEABLE
                    || mode == ContextWrapper.MODE_MULTI_PROCESS) {
                this.mode = mode;
            } else {
                throw new IllegalArgumentException("Mode should be one of these: ContextWrapper.MODE_PRIVATE, ContextWrapper.MODE_WORLD_READABLE, ContextWrapper.MODE_WORLD_WRITEABLE, ContextWrapper.MODE_MULTI_PROCESS");
            }
            return this;
        }

        public void build() {
            if (name == null && mode != -1) {
                name = context.getPackageName();
            }
            if (mode == -1 && name != null) {
                mode = ContextWrapper.MODE_PRIVATE;
            }
            PreferencesHelper.createInstance(this);
        }
    }
}
