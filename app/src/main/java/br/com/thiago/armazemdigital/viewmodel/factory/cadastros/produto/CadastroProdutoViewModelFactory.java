package br.com.thiago.armazemdigital.viewmodel.factory.cadastros.produto;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import br.com.thiago.armazemdigital.database.repository.CategoriaRepository;
import br.com.thiago.armazemdigital.database.repository.FornecedorRepository;
import br.com.thiago.armazemdigital.database.repository.FornecimentoRepository;
import br.com.thiago.armazemdigital.database.repository.ProdutoRepository;
import br.com.thiago.armazemdigital.viewmodel.cadastros.produto.CadastroProdutoViewModel;

public class CadastroProdutoViewModelFactory implements ViewModelProvider.Factory {
    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;
    private final FornecedorRepository fornecedorRepository;
    private final FornecimentoRepository fornecimentoRepository;

    public CadastroProdutoViewModelFactory(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository, FornecedorRepository fornecedorRepository, FornecimentoRepository fornecimentoRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
        this.fornecedorRepository = fornecedorRepository;
        this.fornecimentoRepository = fornecimentoRepository;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(CadastroProdutoViewModel.class)) {
            return (T) new CadastroProdutoViewModel(
                    produtoRepository,
                    categoriaRepository,
                    fornecedorRepository,
                    fornecimentoRepository);
        }

        throw new IllegalArgumentException("ViewModel desconhecido");
    }
}
