package br.com.thiago.armazemdigital.utils.exception;

import static br.com.thiago.armazemdigital.utils.interfaces.Constants.PROPERTY_LOG_DIR_KEY;

import android.content.Context;

import androidx.annotation.NonNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static ArmazemDigitalCrashHandler instance;
    private final Thread.UncaughtExceptionHandler defaultHandler;
    private final Logger mLogger = LoggerFactory.getLogger(ArmazemDigitalCrashHandler.class);
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
        mLogger.error("Exceção não tratada: {}", throwable.getMessage(), throwable);
        saveCrashReport(throwable);

        if(defaultHandler != null) {
            mLogger.error("Finalizando aplicação devido a exceção não tratada...");
            defaultHandler.uncaughtException(thread, throwable);
        }
    }

    /** @noinspection ResultOfMethodCallIgnored*/
    private void saveCrashReport(Throwable throwable) {
        mLogger.info("@saveCrashReport() chamado");
        File crashDir = new File(System.getProperty(PROPERTY_LOG_DIR_KEY), "crash");

        // Remove todos os arquivos de crash anteriores
        deletePreviousCrashReports();

        // Recria diretórios
        crashDir.mkdirs();

        Date now = new Date();
        String timestamp = DateUtils.formatDate(now);
        File crashFile = new File(crashDir, "crash.log");

        try (FileWriter writer = new FileWriter(crashFile, false)) {
            writer.write("Timestamp: " + timestamp + "\n");
            writer.write("Thread: " + Thread.currentThread().getName() + "\n");
            writer.write("Version: " + ((ArmazemDigitalApp) context).getVersionName() + "\n");
            writer.write("Git HASH: " + BuildConfig.GIT_HASH + "\n");
            writer.write("Exception: " + throwable.getMessage() + "\n");
            writer.write("Stack Trace: ");
            throwable.printStackTrace(new PrintWriter(writer));
            mLogger.debug("Crash Report salvo em: {}", crashFile.getAbsolutePath());
        } catch (IOException e) {
            mLogger.debug("Crash Report não salvo: {}", throwable.getMessage(), throwable);
            mLogger.error("Erro ao salvar crash report: {}", e.getMessage(), e);
        }
    }

    /** @noinspection ResultOfMethodCallIgnored*/
    private void deletePreviousCrashReports() {
        mLogger.info("@deletePreviousCrashReports() chamado");
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
