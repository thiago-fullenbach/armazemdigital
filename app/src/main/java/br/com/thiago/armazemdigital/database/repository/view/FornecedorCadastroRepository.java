package br.com.thiago.armazemdigital.database.repository.view;

import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;

import java.util.List;

import br.com.thiago.armazemdigital.database.dao.view.FornecedorCadastroDao;
import br.com.thiago.armazemdigital.model.view.FornecedorCadastro;

public class FornecedorCadastroRepository {
    private final FornecedorCadastroDao fornecedorCadastroDao;

    public FornecedorCadastroRepository(FornecedorCadastroDao fornecedorCadastroDao) {
        this.fornecedorCadastroDao = fornecedorCadastroDao;
    }

    public LiveData<List<FornecedorCadastro>> getFornecedoresCadastroLiveData() {
        return fornecedorCadastroDao.getFornecedoresCadastroLiveData();
    }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    public List<FornecedorCadastro> getFornecedoresCadastro() {
        return fornecedorCadastroDao.getFornecedoresCadastro();
    }
}
