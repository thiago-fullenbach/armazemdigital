package br.com.thiago.armazemdigital.viewmodel.cadastros.produto;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import br.com.thiago.armazemdigital.database.repository.CategoriaRepository;
import br.com.thiago.armazemdigital.database.repository.FornecedorRepository;
import br.com.thiago.armazemdigital.database.repository.FornecimentoRepository;
import br.com.thiago.armazemdigital.database.repository.ProdutoRepository;
import br.com.thiago.armazemdigital.model.Categoria;
import br.com.thiago.armazemdigital.model.Fornecedor;
import br.com.thiago.armazemdigital.model.enums.TipoUnidade;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class CadastroProdutoViewModel extends ViewModel {
    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;
    private final FornecedorRepository fornecedorRepository;
    private final FornecimentoRepository fornecimentoRepository;
    private final MutableLiveData<String> nome = new MutableLiveData<>();
    private final MutableLiveData<String> descricao = new MutableLiveData<>();
    private final MutableLiveData<String> preco = new MutableLiveData<>();
    private final MutableLiveData<TipoUnidade> unidadeMedidaSelecionada = new MutableLiveData<>();
    private final MutableLiveData<List<Categoria>> categoriasDisponiveis = new MutableLiveData<>();
    private final MutableLiveData<Categoria> categoriaSelecionada = new MutableLiveData<>();
    private final MutableLiveData<List<Fornecedor>> fornecedoresDisponiveis = new MutableLiveData<>();
    private final MutableLiveData<List<Fornecedor>> fornecedoresSelecionados = new MutableLiveData<>();
    private final MutableLiveData<Boolean> success = new MutableLiveData<>();

    @Inject
    public CadastroProdutoViewModel(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository, FornecedorRepository fornecedorRepository, FornecimentoRepository fornecimentoRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
        this.fornecedorRepository = fornecedorRepository;
        this.fornecimentoRepository = fornecimentoRepository;

        loadAvailableCategorias();
        loadAvailableFornecedores();
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

    public LiveData<List<Categoria>> getCategoriasDisponiveis() {
        return categoriasDisponiveis;
    }

    public LiveData<Categoria> getCategoriaSelecionada() {
        return categoriaSelecionada;
    }

    public LiveData<List<Fornecedor>> getFornecedoresDisponiveis() {
        return fornecedoresDisponiveis;
    }

    public LiveData<List<Fornecedor>> getFornecedoresSelecionados() {
        return fornecedoresSelecionados;
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

    public void setCategoriaSelecionada(Categoria categoriaProduto) {
        categoriaSelecionada.setValue(categoriaProduto);
    }

    public void loadAvailableCategorias() {
        categoriaRepository.getCategoriasLiveData().observeForever(categoriasDisponiveis::postValue);
    }

    public void loadAvailableFornecedores() {
        fornecedorRepository.getFornecedoresLiveData().observeForever(fornecedoresDisponiveis::postValue);
    }
}
