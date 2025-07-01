package br.com.thiago.armazemdigital.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseListagemViewModel<I> extends ViewModel {
    protected MutableLiveData<List<I>> itens = new MutableLiveData<>(new ArrayList<>());

    protected abstract LiveData<List<I>> getItensFromRepo();
    private final Observer<List<I>> itemObserver = itensBanco -> itens.postValue(itensBanco);

    @Override
    protected void onCleared() {
        super.onCleared();
        getItensFromRepo().removeObserver(itemObserver);
    }

    public LiveData<List<I>> getItens() {
        return itens;
    }

    public final void observeItens() {
        getItensFromRepo().observeForever(itemObserver);
    }
}
