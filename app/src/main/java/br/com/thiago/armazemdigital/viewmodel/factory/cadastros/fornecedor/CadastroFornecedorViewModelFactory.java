package br.com.thiago.armazemdigital.viewmodel.factory.cadastros.fornecedor;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import br.com.thiago.armazemdigital.database.repository.FornecedorRepository;
import br.com.thiago.armazemdigital.viewmodel.cadastros.fornecedor.CadastroFornecedorViewModel;

public class CadastroFornecedorViewModelFactory implements ViewModelProvider.Factory {
    private final FornecedorRepository fornecedorRepository;

    public CadastroFornecedorViewModelFactory(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(CadastroFornecedorViewModel.class)) {
            return (T) new CadastroFornecedorViewModel(fornecedorRepository);
        }
        throw new IllegalArgumentException("ViewModel desconhecido");
    }
}
