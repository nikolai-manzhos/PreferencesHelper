package com.defaultapps.preferenceshelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static junit.framework.TestCase.assertNotNull;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class PreferencesManagerTest {

    @Before
    public void setup() {
        PreferencesHelper
                .builder(RuntimeEnvironment.application.getApplicationContext())
                .build();
    }

    @Test
    public void createPreferencesManager() {
        PreferencesManager manager = new PreferencesManager();
        assertNotNull(manager.getPreferencesHelper());
    }
}
