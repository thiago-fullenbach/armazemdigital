package br.com.thiago.armazemdigital.viewmodel.configuracao;

import android.content.Context;
import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import javax.inject.Inject;

import br.com.thiago.armazemdigital.R;
import br.com.thiago.armazemdigital.preferences.helper.LogPrefsHelper;
import br.com.thiago.armazemdigital.utils.FileLogManagerUtils;
import br.com.thiago.armazemdigital.utils.ZipFileUtils;
import ch.qos.logback.classic.Level;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ConfiguracaoViewModel extends ViewModel {
    private final Logger mLogger = LoggerFactory.getLogger(ConfiguracaoViewModel.class);
    private final LogPrefsHelper mLogPrefsHelper;
    private final MutableLiveData<Level> level = new MutableLiveData<>(Level.ALL);
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>(false);
    private final MutableLiveData<ZipFileUtils.ZipResult> result = new MutableLiveData<>();
    private final Observer<Level> mLogLevelObserver = new Observer<>() {
        @Override
        public void onChanged(Level level) {
            mLogPrefsHelper.setLogLevel(level.toString());
            FileLogManagerUtils.updateFileLogLevel(level.toString());
        }
    };

    @Inject
    public ConfiguracaoViewModel(LogPrefsHelper logPrefsHelper) {
        this.mLogPrefsHelper = logPrefsHelper;
        level.setValue(Level.valueOf(mLogPrefsHelper.getLogLevel()));
        level.observeForever(mLogLevelObserver);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        level.removeObserver(mLogLevelObserver);
    }

    public LiveData<Level> getLevel() {
        return level;
    }

    public LiveData<Boolean> getLoading() {
        return loading;
    }

    public LiveData<ZipFileUtils.ZipResult> getResult() {
        return result;
    }

    public void setLevel(Level level) {
        this.level.setValue(level);
    }

    public void setLoading(boolean loading) {
        this.loading.setValue(loading);
    }

    public void zipLogFile(Context context, String srcPath, Uri destUri) {
        Thread zipThread = new Thread(() -> {
            ZipFileUtils.ZipResult zipResult;
            try {
                ZipFileUtils.zipFolder(context, srcPath, destUri);
                zipResult = new ZipFileUtils.ZipResult(true, null);
            } catch (IOException e) {
                zipResult = new ZipFileUtils.ZipResult(false, context.getString(R.string.error_generating_zip_file));
                mLogger.error("Erro ao compactar arquivo de log: {}", e.getMessage());
            }

            result.postValue(zipResult);
        });

        zipThread.start();
    }

    public void reset() {
        result.setValue(null);
    }
}
