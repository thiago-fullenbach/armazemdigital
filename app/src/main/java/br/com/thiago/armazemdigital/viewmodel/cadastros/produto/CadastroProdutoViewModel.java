package br.com.thiago.armazemdigital.viewmodel.cadastros.produto;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import br.com.thiago.armazemdigital.database.repository.FornecimentoRepository;
import br.com.thiago.armazemdigital.database.repository.ProdutoRepository;
import br.com.thiago.armazemdigital.model.Fornecimento;
import br.com.thiago.armazemdigital.model.Produto;
import br.com.thiago.armazemdigital.model.enums.TipoUnidade;
import br.com.thiago.armazemdigital.utils.LongValidatorUtils;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class CadastroProdutoViewModel extends ViewModel {
    private final ProdutoRepository produtoRepository;
    private final FornecimentoRepository fornecimentoRepository;
    private final MutableLiveData<String> nome = new MutableLiveData<>();
    private final MutableLiveData<String> descricao = new MutableLiveData<>();
    private final MutableLiveData<Long> preco = new MutableLiveData<>();
    private final MutableLiveData<TipoUnidade> unidadeMedidaSelecionada = new MutableLiveData<>();
    private final MutableLiveData<Long> categoriaSelecionadaId = new MutableLiveData<>();
    private final MutableLiveData<List<Long>> fornecedoresSelecionadosId = new MutableLiveData<>();
    private final MutableLiveData<Boolean> success = new MutableLiveData<>();

    @Inject
    public CadastroProdutoViewModel(ProdutoRepository produtoRepository, FornecimentoRepository fornecimentoRepository) {
        this.produtoRepository = produtoRepository;
        this.fornecimentoRepository = fornecimentoRepository;
    }

    public LiveData<String> getNome() {
        return nome;
    }

    public LiveData<String> getDescricao() {
        return descricao;
    }

    public LiveData<Long> getPreco() {
        return preco;
    }

    public LiveData<TipoUnidade> getUnidadeMedidaSelecionada() {
        return unidadeMedidaSelecionada;
    }

    public LiveData<Long> getCategoriaSelecionadaId() {
        return categoriaSelecionadaId;
    }

    public LiveData<List<Long>> getFornecedoresSelecionadosId() {
        return fornecedoresSelecionadosId;
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

    public void setPreco(Long precoProduto) {
        preco.setValue(precoProduto);
    }

    public void setUnidadeMedidaSelecionada(TipoUnidade unidadeMedidaProduto) {
        unidadeMedidaSelecionada.setValue(unidadeMedidaProduto);
    }

    public void setCategoriaSelecionadaId(Long categoriaSelecionadaId) {
        this.categoriaSelecionadaId.setValue(categoriaSelecionadaId);
    }

    public void setFornecedoresSelecionadosId(List<Long> fornecedoresSelecionadosId) {
        this.fornecedoresSelecionadosId.setValue(new ArrayList<>(fornecedoresSelecionadosId));
    }

    public void salvarProduto() {
        Thread saveThread = new Thread(() -> {
            long categoriaId = LongValidatorUtils.unbox(categoriaSelecionadaId.getValue());
            if(categoriaId <= 0) {
                success.postValue(false);
                return;
            }

            Produto produto = new Produto(
                    categoriaSelecionadaId.getValue(),
                    null,
                    nome.getValue(),
                    descricao.getValue(),
                    unidadeMedidaSelecionada.getValue(),
                    preco.getValue(),
                    0L,
                    new Date()
            );

            long produtoId = produtoRepository.insertProduto(produto);
            if(produtoId <= 0) {
                success.postValue(false);
                return;
            }

            salvarFornecimentos(produtoId);
            success.postValue(true);
        });

        saveThread.start();
    }

    public void updateProduto(long id) {
        Thread updateThread = new Thread(() -> {
            Produto produto = produtoRepository.getProduto(id);

            produto.setName(nome.getValue());
            produto.setDescription(descricao.getValue());
            produto.setPrice(preco.getValue());
            produto.setUnit(unidadeMedidaSelecionada.getValue());
            produto.setCategoryId(LongValidatorUtils.unbox(categoriaSelecionadaId.getValue()));

            // Deleta todos os fornecimentos atuais e então salva os novos.
            deleteFornecimentos(produto.getId());
            salvarFornecimentos(produto.getId());

            success.postValue(produtoRepository.updateProduto(produto) > 0);
        });

        updateThread.start();
    }

    public void carregarProduto(long id) {
        Thread loadThread = new Thread(() -> {
            Produto produto = produtoRepository.getProduto(id);

            nome.postValue(produto.getName());
            descricao.postValue(produto.getDescription());
            preco.postValue(produto.getPrice());
            unidadeMedidaSelecionada.postValue(produto.getUnit());
            categoriaSelecionadaId.postValue(produto.getCategoryId());

            loadFornecimentos(produto.getId());
        });

        loadThread.start();
    }

    public void reset() {
        success.setValue(null);
    }

    private void deleteFornecimentos(long produtoId) {
        List<Fornecimento> fornecimentos = fornecimentoRepository.getCurrentFornecimentosForProduct(produtoId);
        if(fornecimentos != null) {
            fornecimentoRepository.deleteFornecimentos(fornecimentos);
        }
    }

    private void loadFornecimentos(long produtoId) {
        List<Fornecimento> fornecimentos = fornecimentoRepository.getCurrentFornecimentosForProduct(produtoId);
        if(fornecimentos != null) {
            List<Long> fornecedoresId = new ArrayList<>();
            fornecimentos.forEach(fornecimento -> fornecedoresId.add(fornecimento.getSupplierId()));
            fornecedoresSelecionadosId.postValue(fornecedoresId);
        }
    }

    private void salvarFornecimentos(long produtoId) {
        List<Fornecimento> fornecimentos = new ArrayList<>();
        List<Long> fornecedoresId = fornecedoresSelecionadosId.getValue();
        if(fornecedoresId != null) {
            fornecedoresId.forEach(fornecedorId -> {
                Fornecimento fornecimento = new Fornecimento(produtoId, fornecedorId);
                fornecimentos.add(fornecimento);
            });

            fornecimentoRepository.insertFornecimentos(fornecimentos);
        }
    }
}
