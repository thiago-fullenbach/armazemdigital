package br.com.thiago.armazemdigital.viewmodel.factory.cadastros.produto;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import br.com.thiago.armazemdigital.database.repository.view.ProdutoCadastroRepository;
import br.com.thiago.armazemdigital.viewmodel.cadastros.produto.ListagemProdutoViewModel;

public class ListagemProdutoViewModelFactory implements ViewModelProvider.Factory {
    private final ProdutoCadastroRepository produtoCadastroRepository;

    public ListagemProdutoViewModelFactory(ProdutoCadastroRepository produtoCadastroRepository) {
        this.produtoCadastroRepository = produtoCadastroRepository;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(ListagemProdutoViewModel.class)) {
            return (T) new ListagemProdutoViewModel(produtoCadastroRepository);
        }

        throw new IllegalArgumentException("ViewModel desconhecido");
    }
}
