package br.com.thiago.armazemdigital.viewmodel.cadastros.produto;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import br.com.thiago.armazemdigital.database.repository.ProdutoRepository;
import br.com.thiago.armazemdigital.model.enums.TipoUnidade;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class CadastroProdutoViewModel extends ViewModel {
    private final ProdutoRepository produtoRepository;
    private final MutableLiveData<String> nome = new MutableLiveData<>();
    private final MutableLiveData<String> descricao = new MutableLiveData<>();
    private final MutableLiveData<String> preco = new MutableLiveData<>();
    private final MutableLiveData<TipoUnidade> unidadeMedidaSelecionada = new MutableLiveData<>();
    private final MutableLiveData<Boolean> success = new MutableLiveData<>();

    @Inject
    public CadastroProdutoViewModel(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public LiveData<String> getNome() {
        return nome;
    }

    public LiveData<String> getDescricao() {
        return descricao;
    }

    public LiveData<String> getPreco() {
        return preco;
    }

    public LiveData<TipoUnidade> getUnidadeMedidaSelecionada() {
        return unidadeMedidaSelecionada;
    }

    public LiveData<Boolean> getSuccess() {
        return success;
    }

    public void setNome(String nomeProduto) {
        nome.setValue(nomeProduto);
    }

    public void setDescricao(String descricaoProduto) {
        descricao.setValue(descricaoProduto);
    }

    public void setPreco(String precoProduto) {
        preco.setValue(precoProduto);
    }

    public void setUnidadeMedidaSelecionada(TipoUnidade unidadeMedidaProduto) {
        unidadeMedidaSelecionada.setValue(unidadeMedidaProduto);
    }
}
