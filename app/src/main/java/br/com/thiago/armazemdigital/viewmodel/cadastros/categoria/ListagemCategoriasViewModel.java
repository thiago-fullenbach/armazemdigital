package br.com.thiago.armazemdigital.viewmodel.cadastros.categoria;

import androidx.lifecycle.LiveData;

import java.util.List;

import br.com.thiago.armazemdigital.database.repository.view.CategoriaCadastroRepository;
import br.com.thiago.armazemdigital.model.view.CategoriaCadastro;
import br.com.thiago.armazemdigital.viewmodel.BaseListagemViewModel;

public class ListagemCategoriasViewModel extends BaseListagemViewModel<CategoriaCadastro> {
    private final CategoriaCadastroRepository categoriaCadastroRepository;

    public ListagemCategoriasViewModel(CategoriaCadastroRepository categoriaCadastroRepository) {
        this.categoriaCadastroRepository = categoriaCadastroRepository;
        observeItens();
    }

    @Override
    protected LiveData<List<CategoriaCadastro>> getItensFromRepo() {
        return categoriaCadastroRepository.getCategoriasCadastroLiveData();
    }
}
