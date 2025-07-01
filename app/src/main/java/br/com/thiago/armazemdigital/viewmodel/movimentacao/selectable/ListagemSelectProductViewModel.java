package br.com.thiago.armazemdigital.viewmodel.movimentacao.selectable;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import br.com.thiago.armazemdigital.database.repository.view.ProdutoCadastroRepository;
import br.com.thiago.armazemdigital.model.view.ProdutoCadastro;
import br.com.thiago.armazemdigital.viewmodel.BaseListagemViewModel;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ListagemSelectProductViewModel extends BaseListagemViewModel<ProdutoCadastro> {
    private final ProdutoCadastroRepository mProdutoCadastroRepository;

    @Inject
    public ListagemSelectProductViewModel(ProdutoCadastroRepository mProdutoCadastroRepository) {
        this.mProdutoCadastroRepository = mProdutoCadastroRepository;
        observeItens();
    }

    @Override
    protected LiveData<List<ProdutoCadastro>> getItensFromRepo() {
        return mProdutoCadastroRepository.getProdutosCadastroLiveData();
    }
}
