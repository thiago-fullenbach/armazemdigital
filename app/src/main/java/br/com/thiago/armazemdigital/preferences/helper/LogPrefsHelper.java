package br.com.thiago.armazemdigital.preferences.helper;

import static br.com.thiago.armazemdigital.utils.interfaces.Constants.KEY_LOG_LEVEL;
import static br.com.thiago.armazemdigital.utils.interfaces.Constants.PREFS_LOG_NAME;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.MainThread;

import org.jetbrains.annotations.TestOnly;

import ch.qos.logback.classic.Level;

/**
 * Classe auxiliar para utilização de shared preferences.
 */
public class LogPrefsHelper {
    private final SharedPreferences prefs;

    public LogPrefsHelper(Application application) {
        prefs = application.getSharedPreferences(PREFS_LOG_NAME, Context.MODE_PRIVATE);
    }

    /**
     * Função para definir o nível de log no arquivo de log.
     *
     * @param level Nível de log.
     */
    @MainThread
    public void setLogLevel(String level) {
        prefs.edit().putString(KEY_LOG_LEVEL, level).apply();
    }

    /**
     * Função para obter o nível de log no arquivo de log.
     *
     * @return Nível de log.
     */
    @MainThread
    public String getLogLevel() {
        return prefs.getString(KEY_LOG_LEVEL, Level.ALL.toString());
    }

    /**
     * Função para limpar os dados do shared preferences.
     */
    @TestOnly
    public void clear() {
        prefs.edit().clear().apply();
    }
}
