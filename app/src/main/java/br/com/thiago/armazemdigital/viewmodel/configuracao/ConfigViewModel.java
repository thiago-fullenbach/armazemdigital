package br.com.thiago.armazemdigital.viewmodel.configuracao;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import br.com.thiago.armazemdigital.preferences.helper.LogPrefsHelper;
import br.com.thiago.armazemdigital.utils.LogUtils;
import ch.qos.logback.classic.Level;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ConfigViewModel extends ViewModel {
    private final LogPrefsHelper mLogPrefsHelper;
    private final MutableLiveData<Level> level = new MutableLiveData<>(Level.ALL);
    private final Observer<Level> logLevelObserver = new Observer<>() {
        @Override
        public void onChanged(Level level) {
            mLogPrefsHelper.setLogLevel(level.toString());
            LogUtils.atualizarNivelLogArquivo(level.toString());
        }
    };

    @Inject
    public ConfigViewModel(LogPrefsHelper logPrefsHelper) {
        this.mLogPrefsHelper = logPrefsHelper;
        level.setValue(Level.valueOf(mLogPrefsHelper.getLogLevel()));
        level.observeForever(logLevelObserver);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        level.removeObserver(logLevelObserver);
    }

    public LiveData<Level> getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level.setValue(level);
    }
}
