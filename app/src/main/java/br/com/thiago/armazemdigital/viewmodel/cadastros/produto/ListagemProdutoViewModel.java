package br.com.thiago.armazemdigital.viewmodel.cadastros.produto;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import br.com.thiago.armazemdigital.database.repository.view.ProdutoCadastroRepository;
import br.com.thiago.armazemdigital.model.view.ProdutoCadastro;
import br.com.thiago.armazemdigital.viewmodel.BaseListagemViewModel;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ListagemProdutoViewModel extends BaseListagemViewModel<ProdutoCadastro> {
    private final ProdutoCadastroRepository produtoCadastroRepository;

    @Inject
    public ListagemProdutoViewModel(ProdutoCadastroRepository produtoCadastroRepository) {
        this.produtoCadastroRepository = produtoCadastroRepository;
        observeItens();
    }

    @Override
    protected LiveData<List<ProdutoCadastro>> getItensFromRepo() {
        return produtoCadastroRepository.getProdutosCadastroLiveData();
    }
}
