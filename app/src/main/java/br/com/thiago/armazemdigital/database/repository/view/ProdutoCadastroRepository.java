package br.com.thiago.armazemdigital.database.repository.view;

import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;

import java.util.List;

import br.com.thiago.armazemdigital.database.dao.view.ProdutoCadastroDao;
import br.com.thiago.armazemdigital.model.view.ProdutoCadastro;

public class ProdutoCadastroRepository {
    private final ProdutoCadastroDao produtoCadastroDao;

    public ProdutoCadastroRepository(ProdutoCadastroDao produtoCadastroDao) {
        this.produtoCadastroDao = produtoCadastroDao;
    }

    public LiveData<List<ProdutoCadastro>> getProdutosCadastroLiveData(int limit, int offset) {
        return produtoCadastroDao.getProdutosCadastroLiveData(limit, offset);
    }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    public List<ProdutoCadastro> getProdutosCadastro(int limit, int offset) {
        return produtoCadastroDao.getProdutosCadastro(limit, offset);
    }
}
