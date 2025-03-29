package br.com.thiago.armazemdigital.viewmodel.cadastros.produto.selectable;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import br.com.thiago.armazemdigital.database.repository.view.FornecedorCadastroRepository;
import br.com.thiago.armazemdigital.model.view.FornecedorCadastro;
import br.com.thiago.armazemdigital.viewmodel.BaseListagemViewModel;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ListagemSelectFornecedorViewModel extends BaseListagemViewModel<FornecedorCadastro> {
    private final FornecedorCadastroRepository fornecedorCadastroRepository;

    @Inject
    public ListagemSelectFornecedorViewModel(FornecedorCadastroRepository fornecedorCadastroRepository) {
        this.fornecedorCadastroRepository = fornecedorCadastroRepository;
        observeItens();
    }

    @Override
    protected LiveData<List<FornecedorCadastro>> getItensFromRepo() {
        return fornecedorCadastroRepository.getFornecedoresCadastroLiveData();
    }
}
