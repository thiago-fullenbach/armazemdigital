package br.com.thiago.armazemdigital.database.repository.view;

import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;

import java.util.List;

import br.com.thiago.armazemdigital.database.dao.view.ProdutoEstoqueDao;
import br.com.thiago.armazemdigital.model.view.ProdutoEstoque;

public class ProdutoEstoqueRepository {
    private final ProdutoEstoqueDao produtoEstoqueDao;

    public ProdutoEstoqueRepository(ProdutoEstoqueDao produtoEstoqueDao) {
        this.produtoEstoqueDao = produtoEstoqueDao;
    }

    public LiveData<List<ProdutoEstoque>> getProdutosEstoqueLiveData(int limit, int offset) {
        return produtoEstoqueDao.getProdutosEstoqueLiveData(limit, offset);
    }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    public List<ProdutoEstoque> getProdutosEstoque(int limit, int offset) {
        return produtoEstoqueDao.getProdutosEstoque(limit, offset);
    }
}
