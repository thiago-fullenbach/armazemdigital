package br.com.thiago.armazemdigital.utils.exception;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import br.com.thiago.armazemdigital.ArmazemDigitalApp;
import br.com.thiago.armazemdigital.BuildConfig;
import br.com.thiago.armazemdigital.utils.DateUtils;
import dagger.hilt.android.qualifiers.ApplicationContext;

public class ArmazemDigitalCrashHandler implements Thread.UncaughtExceptionHandler {
    private static final String TAG = "CrashHandler";
    private static ArmazemDigitalCrashHandler instance;
    private final Thread.UncaughtExceptionHandler defaultHandler;
    @ApplicationContext
    private final Context context;

    private ArmazemDigitalCrashHandler(@ApplicationContext Context context) {
        this.context = context;
        this.defaultHandler = Thread.getDefaultUncaughtExceptionHandler();
    }

    public static ArmazemDigitalCrashHandler getInstance(@ApplicationContext Context context) {
        if(instance == null) {
            instance = new ArmazemDigitalCrashHandler(context);
        }

        return instance;
    }

    @Override
    public void uncaughtException(@NonNull Thread thread, @NonNull Throwable throwable) {
        Log.e(TAG, "Uncaught exception: " + throwable.getMessage(), throwable);
        saveCrashReport(throwable);

        if(defaultHandler != null) {
            defaultHandler.uncaughtException(thread, throwable);
        }
    }

    /** @noinspection ResultOfMethodCallIgnored*/
    private void saveCrashReport(Throwable throwable) {
        File crashDir = new File(context.getFilesDir(), "logs/crash");

        // Remove todos os arquivos de crash anteriores
        deletePreviousCrashReports();

        // Recria diretórios
        crashDir.mkdirs();

        Date now = new Date();
        String timestampFileName = DateUtils.formatLogDate(now);
        String timestampFileContent = DateUtils.formatDate(now);
        File crashFile = new File(crashDir, "crash_" + timestampFileName + ".txt");

        try (FileWriter writer = new FileWriter(crashFile, false)) {
            writer.write("Timestamp: " + timestampFileContent + "\n");
            writer.write("Thread: " + Thread.currentThread().getName() + "\n");
            writer.write("Version: " + ((ArmazemDigitalApp) context).getVersionName() + "\n");
            writer.write("Git HASH: " + BuildConfig.GIT_HASH + "\n");
            writer.write("Exception: " + throwable.getMessage() + "\n");
            writer.write("Stack Trace: ");
            throwable.printStackTrace(new PrintWriter(writer));
        } catch (IOException e) {
            Log.i(TAG, "Crash Report não salvo: " + throwable.getMessage(), throwable);
            Log.e(TAG, "Erro ao salvar crash report: " + e.getMessage(), e);
        }
    }

    /** @noinspection ResultOfMethodCallIgnored*/
    private void deletePreviousCrashReports() {
        File crashDir = new File(context.getFilesDir(), "logs/crash");
        if (crashDir.exists()) {
            File[] crashFiles = crashDir.listFiles();
            if (crashFiles != null) {
                for (File file : crashFiles) {
                    file.delete();
                }
            }

            crashDir.delete();
        }
    }
}
