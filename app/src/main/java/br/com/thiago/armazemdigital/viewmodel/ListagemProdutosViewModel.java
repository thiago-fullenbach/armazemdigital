package br.com.thiago.armazemdigital.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import br.com.thiago.armazemdigital.database.repository.view.ProdutoCadastroRepository;
import br.com.thiago.armazemdigital.model.view.ProdutoCadastro;
import br.com.thiago.armazemdigital.utils.IntegerUtil;

public class ListagemProdutosViewModel extends ViewModel {
    private static final int ITENS_POR_PAGINA = 10;
    private final ProdutoCadastroRepository produtoCadastroRepository;
    private final MutableLiveData<List<ProdutoCadastro>> _produtosCadastrados = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<Integer> _paginaAtual = new MutableLiveData<>(0);
    private final MutableLiveData<Boolean> _isLoading = new MutableLiveData<>(false);
    public LiveData<Integer> paginaAtual = _paginaAtual;
    public LiveData<Boolean> isLoading = _isLoading;
    public LiveData<List<ProdutoCadastro>> produtosCadastrados = _produtosCadastrados;

    public ListagemProdutosViewModel(ProdutoCadastroRepository produtoCadastroRepository) {
        this.produtoCadastroRepository = produtoCadastroRepository;
        loadMoreItens();
    }

    public void loadMoreItens() {
        if(Boolean.TRUE.equals(_isLoading.getValue())) return;

        Thread loadingThread = new Thread(() -> {
            List<ProdutoCadastro> produtos = produtosCadastrados.getValue() != null ? produtosCadastrados.getValue() : new ArrayList<>();
            List<ProdutoCadastro> produtosBanco = produtoCadastroRepository.getProdutosCadastro(ITENS_POR_PAGINA, IntegerUtil.unboxInteger(_paginaAtual.getValue()));

            produtos.addAll(produtosBanco);

            _produtosCadastrados.postValue(produtos);
            produtosCadastrados = _produtosCadastrados;

            _isLoading.postValue(false);
            isLoading = _isLoading;

            _paginaAtual.postValue(IntegerUtil.unboxInteger(_paginaAtual.getValue()) + 1);
            paginaAtual = _paginaAtual;
        });

        _isLoading.setValue(true);
        loadingThread.start();
    }
}
