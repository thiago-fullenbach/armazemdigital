package br.com.thiago.armazemdigital.viewmodel.cadastros.categoria;

import androidx.lifecycle.LiveData;

import java.util.List;

import br.com.thiago.armazemdigital.database.repository.CategoriaRepository;
import br.com.thiago.armazemdigital.model.Categoria;
import br.com.thiago.armazemdigital.viewmodel.BaseListagemViewModel;

public class ListagemCategoriasViewModel extends BaseListagemViewModel<Categoria> {
    private final CategoriaRepository categoriaRepository;

    public ListagemCategoriasViewModel(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
        loadMoreItens();
    }

    @Override
    protected LiveData<List<Categoria>> getItensFromRepo(int page) {
        return categoriaRepository.getCategoriasLiveData(BaseListagemViewModel.DEFAULT_ITENS_POR_PAGINA, page);
    }
}
