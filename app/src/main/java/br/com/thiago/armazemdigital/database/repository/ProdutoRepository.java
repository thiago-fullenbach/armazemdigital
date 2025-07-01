package br.com.thiago.armazemdigital.database.repository;

import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;

import java.util.List;

import br.com.thiago.armazemdigital.database.dao.ProdutoDao;
import br.com.thiago.armazemdigital.model.Produto;

public class ProdutoRepository {
    private final ProdutoDao produtoDao;

    public ProdutoRepository(ProdutoDao produtoDao) {
        this.produtoDao = produtoDao;
    }

    public long insertProduto(Produto produto) {
        return produtoDao.insert(produto);
    }

    public int updateProduto(Produto produto) {
        return produtoDao.update(produto);
    }

    public int deleteProduto(Produto produto) {
        return produtoDao.delete(produto);
    }

    public LiveData<List<Produto>> getProdutosLiveData() {
        return produtoDao.getProdutosLiveData();
    }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    public List<Produto> getProdutos() {
        return produtoDao.getProdutos();
    }

    public Produto getProduto(long id) {
        return produtoDao.getProduto(id);
    }
}
