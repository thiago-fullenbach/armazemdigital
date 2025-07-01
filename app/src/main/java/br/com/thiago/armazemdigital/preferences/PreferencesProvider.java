package br.com.thiago.armazemdigital.preferences;

import android.app.Application;

import javax.inject.Singleton;

import br.com.thiago.armazemdigital.preferences.helper.LogPrefsHelper;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class PreferencesProvider {
    @Provides
    @Singleton
    public static LogPrefsHelper provideLogPrefsHelper(Application application) {
        return new LogPrefsHelper(application);
    }
}
