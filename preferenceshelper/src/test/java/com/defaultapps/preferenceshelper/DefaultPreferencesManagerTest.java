package com.defaultapps.preferenceshelper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class DefaultPreferencesManagerTest {


    @Test
    public void setup() {
        PreferencesHelper
                .builder(RuntimeEnvironment.application.getApplicationContext())
                .build();
    }

    @Test
    public void createDefaultPreferencesManager() {
        DefaultPreferencesManager manager = new DefaultPreferencesManager();
        assertNotNull(manager.getPreferencesHelper());
    }

    @Test
    public void shouldSetFirstTimeUser() {
        final boolean FIRST_TIME_USER = false;
        DefaultPreferencesManager manager = new DefaultPreferencesManager();
        manager.setFirstTimeUser(FIRST_TIME_USER);
        assertEquals(FIRST_TIME_USER, manager.getFirstTimeUser());
    }
}
