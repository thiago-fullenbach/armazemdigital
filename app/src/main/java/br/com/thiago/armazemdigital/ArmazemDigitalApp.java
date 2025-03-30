package br.com.thiago.armazemdigital;

import android.app.Application;
import android.content.pm.PackageManager;

import br.com.thiago.armazemdigital.utils.exception.ArmazemDigitalCrashHandler;
import dagger.hilt.android.HiltAndroidApp;

/**
 * Classe Application do App
 */
@HiltAndroidApp
public class ArmazemDigitalApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Configuração do handler de exceções não tratadas
        Thread.setDefaultUncaughtExceptionHandler(ArmazemDigitalCrashHandler.getInstance(this));
    }

    /** Função para obter o versionName da aplicação.
     *
     * @return Versão atual do app (String).
     */
    public String getVersionName() {
        PackageManager packageManager = getPackageManager();
        try {
            return packageManager.getPackageInfo(getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return getString(R.string.n_f);
        }
    }
}
