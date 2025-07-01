package br.com.thiago.armazemdigital.viewmodel.cadastros.produto.selectable;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import br.com.thiago.armazemdigital.database.repository.view.CategoriaCadastroRepository;
import br.com.thiago.armazemdigital.model.view.CategoriaCadastro;
import br.com.thiago.armazemdigital.viewmodel.BaseListagemViewModel;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ListagemSelectCategoryViewModel extends BaseListagemViewModel<CategoriaCadastro> {
    private final CategoriaCadastroRepository categoriaCadastroRepository;

    @Inject
    public ListagemSelectCategoryViewModel(CategoriaCadastroRepository categoriaCadastroRepository) {
        this.categoriaCadastroRepository = categoriaCadastroRepository;
        observeItens();
    }

    @Override
    protected LiveData<List<CategoriaCadastro>> getItensFromRepo() {
        return categoriaCadastroRepository.getCategoriasCadastroLiveData();
    }
}
