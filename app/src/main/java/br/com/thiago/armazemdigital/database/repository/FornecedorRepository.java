package br.com.thiago.armazemdigital.database.repository;

import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;

import java.util.List;

import br.com.thiago.armazemdigital.database.dao.FornecedorDao;
import br.com.thiago.armazemdigital.model.Fornecedor;

public class FornecedorRepository {
    private final FornecedorDao fornecedorDao;

    public FornecedorRepository(FornecedorDao fornecedorDao) {
        this.fornecedorDao = fornecedorDao;
    }

    public long insertFornecedor(Fornecedor fornecedor) {
        return fornecedorDao.insert(fornecedor);
    }

    public void updateFornecedor(Fornecedor fornecedor) {
        fornecedorDao.update(fornecedor);
    }

    public void deleteFornecedor(Fornecedor fornecedor) {
        fornecedorDao.delete(fornecedor);
    }

    public LiveData<List<Fornecedor>> getFornecedoresLiveData() {
        return fornecedorDao.getFornecedoresLiveData();
    }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    public List<Fornecedor> getFornecedores() {
        return fornecedorDao.getFornecedores();
    }
}
