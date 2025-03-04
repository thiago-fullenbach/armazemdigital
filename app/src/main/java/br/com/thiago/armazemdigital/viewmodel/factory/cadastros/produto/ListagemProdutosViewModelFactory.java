package br.com.thiago.armazemdigital.viewmodel.factory.cadastros.produto;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import br.com.thiago.armazemdigital.database.repository.view.ProdutoCadastroRepository;
import br.com.thiago.armazemdigital.viewmodel.cadastros.produto.ListagemProdutosViewModel;

public class ListagemProdutosViewModelFactory implements ViewModelProvider.Factory {
    private final ProdutoCadastroRepository produtoCadastroRepository;

    public ListagemProdutosViewModelFactory(ProdutoCadastroRepository produtoCadastroRepository) {
        this.produtoCadastroRepository = produtoCadastroRepository;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(ListagemProdutosViewModel.class)) {
            return (T) new ListagemProdutosViewModel(produtoCadastroRepository);
        }

        throw new IllegalArgumentException("ViewModel desconhecido");
    }
}
