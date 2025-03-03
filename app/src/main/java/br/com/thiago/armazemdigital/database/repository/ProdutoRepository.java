package br.com.thiago.armazemdigital.database.repository;

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

    public void updateProduto(Produto produto) {
        produtoDao.update(produto);
    }

    public void deleteProduto(Produto produto) {
        produtoDao.delete(produto);
    }

    public List<Produto> getProdutos(int limit, int offset) {
        return produtoDao.getProdutos(limit, offset);
    }

    public Produto getProduto(long id) {
        return produtoDao.getProduto(id);
    }
}
