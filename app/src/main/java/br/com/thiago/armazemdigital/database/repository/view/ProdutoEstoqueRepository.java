package br.com.thiago.armazemdigital.database.repository.view;

import java.util.List;

import br.com.thiago.armazemdigital.database.dao.view.ProdutoEstoqueDao;
import br.com.thiago.armazemdigital.model.view.ProdutoEstoque;

public class ProdutoEstoqueRepository {
    private final ProdutoEstoqueDao produtoEstoqueDao;

    public ProdutoEstoqueRepository(ProdutoEstoqueDao produtoEstoqueDao) {
        this.produtoEstoqueDao = produtoEstoqueDao;
    }

    public List<ProdutoEstoque> getProdutosEstoque(int limit, int offset) {
        return produtoEstoqueDao.getProdutosEstoque(limit, offset);
    }
}
