package br.com.thiago.armazemdigital.database.repository.view;

import java.util.List;

import br.com.thiago.armazemdigital.database.dao.view.ProdutoCadastroDao;
import br.com.thiago.armazemdigital.model.view.ProdutoCadastro;

public class ProdutoCadastroRepository {
    private final ProdutoCadastroDao produtoCadastroDao;

    public ProdutoCadastroRepository(ProdutoCadastroDao produtoCadastroDao) {
        this.produtoCadastroDao = produtoCadastroDao;
    }

    public List<ProdutoCadastro> getProdutosCadastro(int limit, int offset) {
        return produtoCadastroDao.getProdutosCadastro(limit, offset);
    }
}
