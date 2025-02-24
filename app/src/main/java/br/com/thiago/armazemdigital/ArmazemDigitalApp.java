package br.com.thiago.armazemdigital;

import android.app.Application;
import android.content.pm.PackageManager;

public class ArmazemDigitalApp extends Application {
    /** Função para obter o versionName da aplicação.
     *
     * @return Versão atual do app (String).
     */
    public String getVersionName() {
        PackageManager packageManager = getPackageManager();
        try {
            return packageManager.getPackageInfo(getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return getString(R.string.n_a);
        }
    }
}
