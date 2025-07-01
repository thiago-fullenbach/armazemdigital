package br.com.thiago.armazemdigital.viewmodel.estoque;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import br.com.thiago.armazemdigital.database.repository.view.ProdutoEstoqueRepository;
import br.com.thiago.armazemdigital.model.view.ProdutoEstoque;
import br.com.thiago.armazemdigital.viewmodel.BaseListagemViewModel;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class EstoqueViewModel extends BaseListagemViewModel<ProdutoEstoque> {
    private final ProdutoEstoqueRepository mRepository;

    @Inject
    public EstoqueViewModel(ProdutoEstoqueRepository mRepository) {
        this.mRepository = mRepository;
        observeItens();
    }

    @Override
    protected LiveData<List<ProdutoEstoque>> getItensFromRepo() {
        return mRepository.getProdutosEstoqueLiveData();
    }
}
