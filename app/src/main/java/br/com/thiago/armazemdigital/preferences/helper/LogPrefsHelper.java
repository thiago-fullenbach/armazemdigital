package br.com.thiago.armazemdigital.preferences.helper;

import static br.com.thiago.armazemdigital.utils.interfaces.Constants.KEY_LOG_LEVEL;
import static br.com.thiago.armazemdigital.utils.interfaces.Constants.PREFS_LOG_NAME;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.MainThread;

import ch.qos.logback.classic.Level;

public class LogPrefsHelper {
    private final SharedPreferences prefs;

    public LogPrefsHelper(Application application) {
        prefs = application.getSharedPreferences(PREFS_LOG_NAME, Context.MODE_PRIVATE);
    }

    @MainThread
    public void setLogLevel(String level) {
        prefs.edit().putString(KEY_LOG_LEVEL, level).apply();
    }

    @MainThread
    public String getLogLevel() {
        return prefs.getString(KEY_LOG_LEVEL, Level.ALL.toString());
    }
}
