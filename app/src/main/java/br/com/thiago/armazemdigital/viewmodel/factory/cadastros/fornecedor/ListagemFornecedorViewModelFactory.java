package br.com.thiago.armazemdigital.viewmodel.factory.cadastros.fornecedor;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import br.com.thiago.armazemdigital.database.repository.view.FornecedorCadastroRepository;
import br.com.thiago.armazemdigital.viewmodel.cadastros.fornecedor.ListagemFornecedorViewModel;

public class ListagemFornecedorViewModelFactory implements ViewModelProvider.Factory {
    private final FornecedorCadastroRepository fornecedorCadastroRepository;

    public ListagemFornecedorViewModelFactory(FornecedorCadastroRepository fornecedorCadastroRepository) {
        this.fornecedorCadastroRepository = fornecedorCadastroRepository;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ListagemFornecedorViewModel.class)) {
            return (T) new ListagemFornecedorViewModel(fornecedorCadastroRepository);
        }
        throw new IllegalArgumentException("ViewModel desconhecido");
    }
}
