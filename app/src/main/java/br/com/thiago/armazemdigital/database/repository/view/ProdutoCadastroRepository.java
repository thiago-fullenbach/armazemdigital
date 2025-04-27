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

    public ProdutoCadastro getProdutoCadastro(long id) {
        return produtoCadastroDao.getProdutoCadastro(id);
    }

    public LiveData<List<ProdutoCadastro>> getProdutosCadastroLiveData() {
        return produtoCadastroDao.getProdutosCadastroLiveData();
    }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    public List<ProdutoCadastro> getProdutosCadastro() {
        return produtoCadastroDao.getProdutosCadastro();
    }
}
