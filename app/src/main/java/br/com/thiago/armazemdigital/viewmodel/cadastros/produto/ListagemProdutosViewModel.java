package br.com.thiago.armazemdigital.viewmodel.cadastros.produto;

import androidx.lifecycle.LiveData;

import java.util.List;

import br.com.thiago.armazemdigital.database.repository.view.ProdutoCadastroRepository;
import br.com.thiago.armazemdigital.model.view.ProdutoCadastro;
import br.com.thiago.armazemdigital.viewmodel.BaseListagemViewModel;

public class ListagemProdutosViewModel extends BaseListagemViewModel<ProdutoCadastro> {
    private final ProdutoCadastroRepository produtoCadastroRepository;

    public ListagemProdutosViewModel(ProdutoCadastroRepository produtoCadastroRepository) {
        this.produtoCadastroRepository = produtoCadastroRepository;
        observeItens();
    }

    @Override
    protected LiveData<List<ProdutoCadastro>> getItensFromRepo() {
        return produtoCadastroRepository.getProdutosCadastroLiveData();
    }
}
