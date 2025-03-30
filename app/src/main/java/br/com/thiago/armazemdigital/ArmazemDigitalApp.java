package br.com.thiago.armazemdigital;

import static br.com.thiago.armazemdigital.utils.interfaces.Constants.PROPERTY_LOG_DIR_KEY;

import android.app.Application;
import android.content.pm.PackageManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.thiago.armazemdigital.utils.exception.ArmazemDigitalCrashHandler;
import dagger.hilt.android.HiltAndroidApp;

/**
 * Classe Application do App
 */
@HiltAndroidApp
public class ArmazemDigitalApp extends Application {
    private Logger mLogger;

    @Override
    public void onCreate() {
        super.onCreate();

        // Define o diretório onde os logs serão armazenados
        System.setProperty(PROPERTY_LOG_DIR_KEY, getFilesDir() + "/logs");

        // Configuração do handler de exceções não tratadas
        Thread.setDefaultUncaughtExceptionHandler(ArmazemDigitalCrashHandler.getInstance(this));

        mLogger = LoggerFactory.getLogger(ArmazemDigitalApp.class);
        mLogger.info("ArmazemDigital Iniciado - Versão: {}", getVersionName());
    }

    /**
     * Função para obter o versionName da aplicação.
     *
     * @return Versão atual do app (String).
     */
    public String getVersionName() {
        PackageManager packageManager = getPackageManager();
        try {
            return packageManager.getPackageInfo(getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            mLogger.error("Erro ao obter versão do app", e);
            return getString(R.string.n_f);
        }
    }
}
