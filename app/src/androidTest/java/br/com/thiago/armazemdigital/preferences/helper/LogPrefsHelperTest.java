package br.com.thiago.armazemdigital.preferences.helper;

import static org.junit.Assert.assertEquals;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import ch.qos.logback.classic.Level;

@RunWith(AndroidJUnit4.class)
public class LogPrefsHelperTest {
    private LogPrefsHelper logPrefsHelper;

    @Before
    public void setUp() throws Exception {
        logPrefsHelper = new LogPrefsHelper(ApplicationProvider.getApplicationContext());
        logPrefsHelper.clear();
    }

    @Test
    public void testSetLogLevel() {
        logPrefsHelper.setLogLevel(Level.INFO.toString());

        String level = logPrefsHelper.getLogLevel();
        assertEquals(Level.INFO.toString(), level);
    }

    @Test
    public void testGetLogLevel() {
        String level = logPrefsHelper.getLogLevel();
        assertEquals(Level.ALL.toString(), level);
    }
}