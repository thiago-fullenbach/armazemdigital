package br.com.thiago.armazemdigital;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.room.Room;

import br.com.thiago.armazemdigital.database.ArmazemDigitalDb;

/**
 * Classe Application do App
 */
public class ArmazemDigitalApp extends Application {
    // Deve ser volátil para torná-lo thread-safe
    private static volatile ArmazemDigitalDb dbInstance;

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

    /** Obtém a instância do banco de dados existente, se existir.
     * Caso o contrário, inicializa a instância.
     *
     * @return Instância do banco de dados do app.
     */
    public static ArmazemDigitalDb getDbInstance(Context appContext) {
        ArmazemDigitalDb localInstance = dbInstance;
        if(localInstance != null) {
            return localInstance;
        }

        synchronized (ArmazemDigitalApp.class) {
            if(dbInstance == null) {
                dbInstance = Room.databaseBuilder(
                        appContext,
                        ArmazemDigitalDb.class,
                        appContext.getString(R.string.db_file_name)).build();
            }

            return dbInstance;
        }
    }
}
