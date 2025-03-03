package br.com.thiago.armazemdigital.viewmodel.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import br.com.thiago.armazemdigital.database.repository.view.ProdutoCadastroRepository;
import br.com.thiago.armazemdigital.viewmodel.ListagemCadastradoProdutosViewModel;

public class ListagemCadastradoProdutosViewModelFactory implements ViewModelProvider.Factory {
    private final ProdutoCadastroRepository produtoCadastroRepository;

    public ListagemCadastradoProdutosViewModelFactory(ProdutoCadastroRepository produtoCadastroRepository) {
        this.produtoCadastroRepository = produtoCadastroRepository;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(ListagemCadastradoProdutosViewModel.class)) {
            return (T) new ListagemCadastradoProdutosViewModel(produtoCadastroRepository);
        }

        throw new IllegalArgumentException("ViewModel desconhecido");
    }
}
