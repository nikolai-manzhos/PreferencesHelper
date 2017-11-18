package com.defaultapps.preferenceshelper;

import android.content.Context;
import android.content.ContextWrapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class PreferencesHelperTest {

    private PreferencesHelper preferencesHelper;

    @Before
    public void setup() {
        final String SP_NAME = "TEST_SP";
        final int SP_MODE = 0;
        PreferencesHelper
                .builder(RuntimeEnvironment.application.getApplicationContext())
                .setMode(SP_MODE)
                .setName(SP_NAME)
                .build();

        preferencesHelper = PreferencesHelper.getInstance();
    }

    @Test(expected = IllegalArgumentException.class)
    public void passNonApplicationContext() {
        Context context = mock(Context.class);
        PreferencesHelper.builder(context)
                .build();
    }

    @Test(expected = RuntimeException.class)
    public void tryToGetNullInstance() {
        PreferencesHelper.instance = null;
        PreferencesHelper.getInstance();
    }

    @Test(expected = IllegalArgumentException.class)
    public void initWithWrongMode() {
        final int FAKE_MODE = 1232;
        PreferencesHelper
                .builder(RuntimeEnvironment.application.getApplicationContext())
                .setMode(FAKE_MODE)
                .build();
    }

    @Test
    public void initWithDefaultBuilder() {
        PreferencesHelper
                .builder(RuntimeEnvironment.application.getApplicationContext())
                .build();
    }

    @Test
    public void initWithDefaultName() {
        PreferencesHelper
                .builder(RuntimeEnvironment.application.getApplicationContext())
                .setMode(ContextWrapper.MODE_PRIVATE)
                .build();
    }

    @Test
    @SuppressWarnings("deprecation")
    public void shouldSetAllModes() {
        constructPreferencesHelperWithMode(ContextWrapper.MODE_WORLD_READABLE);
        constructPreferencesHelperWithMode(ContextWrapper.MODE_WORLD_WRITEABLE);
        constructPreferencesHelperWithMode(ContextWrapper.MODE_MULTI_PROCESS);
    }

    @Test
    public void initWithDefaultMode() {
        final String SP_NAME = "test_sp";
        PreferencesHelper
                .builder(RuntimeEnvironment.application.getApplicationContext())
                .setName(SP_NAME)
                .build();
    }

    @Test
    public void shouldPutInt() {
        final String TEST_INT_KEY = "p_test_integer";
        final int TEST_INT = 4;
        final int TEST_DEFAULT_VALUE = 0;
        preferencesHelper.putInt(TEST_INT_KEY, TEST_INT);

        assertEquals(TEST_INT, preferencesHelper.getInt(TEST_INT_KEY));
        assertEquals(TEST_INT, preferencesHelper.getInt(TEST_INT_KEY, TEST_DEFAULT_VALUE));
    }

    @Test
    public void shouldPutString() {
        final String TEST_STRING_KEY = "p_test_string";
        final String TEST_STRING = "hello";
        final String TEST_DEFAULT_STRING = " ";
        preferencesHelper.putString(TEST_STRING_KEY, TEST_STRING);

        assertEquals(TEST_STRING, preferencesHelper.getString(TEST_STRING_KEY));
        assertEquals(TEST_STRING, preferencesHelper.getString(TEST_STRING_KEY, TEST_DEFAULT_STRING));
    }

    @Test
    public void shouldPutBoolean() {
        final String TEST_BOOLEAN_KEY = "p_test_boolean";
        final boolean TEST_BOOLEAN = true;
        final boolean TEST_DEFAULT_BOOLEAN = false;
        preferencesHelper.putBoolean(TEST_BOOLEAN_KEY, TEST_BOOLEAN);

        assertEquals(TEST_BOOLEAN, preferencesHelper.getBoolean(TEST_BOOLEAN_KEY));
        assertEquals(TEST_BOOLEAN, preferencesHelper.getBoolean(TEST_BOOLEAN_KEY, TEST_DEFAULT_BOOLEAN));
    }

    @Test
    public void shouldPutLong() {
        final String TEST_LONG_KEY = "p_test_long";
        final long TEST_LONG = 1000L;
        final long TEST_DEFAULT_LONG = 0L;
        preferencesHelper.putLong(TEST_LONG_KEY, TEST_LONG);

        assertEquals(TEST_LONG, preferencesHelper.getLong(TEST_LONG_KEY));
        assertEquals(TEST_LONG, preferencesHelper.getLong(TEST_LONG_KEY, TEST_DEFAULT_LONG));
    }

    @Test
    public void shouldPutStingSet() {
        final String TEST_SET_KEY = "p_test_set";
        final Set<String> TEST_SET = new HashSet<>();
        TEST_SET.add("1");
        TEST_SET.add("2");
        final Set<String> TEST_DEFAULT_SET = new HashSet<>();
        preferencesHelper.putStringSet(TEST_SET_KEY, TEST_SET);

        assertEquals(TEST_SET, preferencesHelper.getStringSet(TEST_SET_KEY));
        assertEquals(TEST_SET, preferencesHelper.getStringSet(TEST_SET_KEY, TEST_DEFAULT_SET));
    }

    @Test
    public void shouldPutStringList() {
        final String TEST_LIST_KEY = "p_test_list";
        final List<String> TEST_LIST = new ArrayList<>();
        TEST_LIST.add("One;");
        TEST_LIST.add("Two");
        TEST_LIST.add("Three\"");
        final List<String> TEST_DEFAULT_LIST = new ArrayList<>();
        preferencesHelper.putStringList(TEST_LIST_KEY, TEST_LIST);

        assertEquals(TEST_LIST, preferencesHelper.getStringList(TEST_LIST_KEY, TEST_DEFAULT_LIST));
        assertEquals(TEST_LIST, preferencesHelper.getStringList(TEST_LIST_KEY));
    }

    private void constructPreferencesHelperWithMode(int mode) {
        PreferencesHelper
                .builder(RuntimeEnvironment.application.getApplicationContext())
                .setMode(mode)
                .build();
    }
}
