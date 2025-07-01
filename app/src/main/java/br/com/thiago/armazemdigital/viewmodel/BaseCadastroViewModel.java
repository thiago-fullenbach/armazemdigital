package br.com.thiago.armazemdigital.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BaseCadastroViewModel extends ViewModel {
    private final MutableLiveData<Boolean> mCarregado = new MutableLiveData<>(false);

    public LiveData<Boolean> getCarregado() {
        return mCarregado;
    }

    public void setCarregado(boolean carregado) {
        mCarregado.setValue(carregado);
    }
}
