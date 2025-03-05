package br.com.thiago.armazemdigital.viewmodel.factory.cadastros.categoria;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import br.com.thiago.armazemdigital.database.repository.CategoriaRepository;
import br.com.thiago.armazemdigital.viewmodel.cadastros.categoria.CadastroCategoriaViewModel;

public class CadastroCategoriaViewModelFactory implements ViewModelProvider.Factory {
    private final CategoriaRepository categoriaRepository;

    public CadastroCategoriaViewModelFactory(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(CadastroCategoriaViewModel.class)) {
            return (T) new CadastroCategoriaViewModel(categoriaRepository);
        }
        throw new IllegalArgumentException("ViewModel desconhecido");
    }
}
