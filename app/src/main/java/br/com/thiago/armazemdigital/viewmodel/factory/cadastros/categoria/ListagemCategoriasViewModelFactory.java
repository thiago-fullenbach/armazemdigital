package br.com.thiago.armazemdigital.viewmodel.factory.cadastros.categoria;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import br.com.thiago.armazemdigital.database.repository.CategoriaRepository;
import br.com.thiago.armazemdigital.viewmodel.cadastros.categoria.ListagemCategoriasViewModel;

public class ListagemCategoriasViewModelFactory implements ViewModelProvider.Factory {
    private final CategoriaRepository categoriaRepository;

    public ListagemCategoriasViewModelFactory(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ListagemCategoriasViewModel.class)) {
            return (T) new ListagemCategoriasViewModel(categoriaRepository);
        }
        throw new IllegalArgumentException("ViewModel desconhecido");
    }
}
