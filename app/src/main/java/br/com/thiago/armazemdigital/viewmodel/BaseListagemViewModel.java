package br.com.thiago.armazemdigital.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import br.com.thiago.armazemdigital.utils.IntegerUtil;

public abstract class BaseListagemViewModel<I> extends ViewModel {
    protected static final int DEFAULT_ITENS_POR_PAGINA = 10;
    protected final MutableLiveData<List<I>> _itens = new MutableLiveData<>(new ArrayList<>());
    protected final MutableLiveData<Integer> _paginaAtual = new MutableLiveData<>(0);
    protected final MutableLiveData<Boolean> _isLoading = new MutableLiveData<>(false);
    public LiveData<List<I>> itens = _itens;
    public LiveData<Integer> paginaAtual = _paginaAtual;
    public LiveData<Boolean> isLoading = _isLoading;

    protected abstract LiveData<List<I>> getItensFromRepo(int page);

    public final void loadMoreItens() {
        if(Boolean.TRUE.equals(_isLoading.getValue())) return;

        Thread loadingThread = new Thread(() -> {
            LiveData<List<I>> itensBanco = getItensFromRepo(IntegerUtil.unboxInteger(paginaAtual.getValue()) * DEFAULT_ITENS_POR_PAGINA);

            _itens.postValue(itensBanco.getValue());
            itens = _itens;

            _isLoading.postValue(false);
            isLoading = _isLoading;

            _paginaAtual.postValue(IntegerUtil.unboxInteger(_paginaAtual.getValue()) + 1);
            paginaAtual = _paginaAtual;
        });

        _isLoading.setValue(true);
        loadingThread.start();
    }
}
