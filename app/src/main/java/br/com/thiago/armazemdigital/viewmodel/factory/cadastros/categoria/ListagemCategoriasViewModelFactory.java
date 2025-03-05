package br.com.thiago.armazemdigital.viewmodel.factory.cadastros.categoria;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import br.com.thiago.armazemdigital.database.repository.view.CategoriaCadastroRepository;
import br.com.thiago.armazemdigital.viewmodel.cadastros.categoria.ListagemCategoriasViewModel;

public class ListagemCategoriasViewModelFactory implements ViewModelProvider.Factory {
    private final CategoriaCadastroRepository categoriaCadastroRepository;

    public ListagemCategoriasViewModelFactory(CategoriaCadastroRepository categoriaCadastroRepository) {
        this.categoriaCadastroRepository = categoriaCadastroRepository;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ListagemCategoriasViewModel.class)) {
            return (T) new ListagemCategoriasViewModel(categoriaCadastroRepository);
        }
        throw new IllegalArgumentException("ViewModel desconhecido");
    }
}
