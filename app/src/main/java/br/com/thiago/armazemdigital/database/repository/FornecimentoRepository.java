package br.com.thiago.armazemdigital.database.repository;

import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;

import java.util.List;

import br.com.thiago.armazemdigital.database.dao.FornecimentoDao;
import br.com.thiago.armazemdigital.model.Fornecimento;

public class FornecimentoRepository {
    private final FornecimentoDao fornecimentoDao;

    public FornecimentoRepository(FornecimentoDao fornecimentoDao) {
        this.fornecimentoDao = fornecimentoDao;
    }

    public long insertFornecimento(Fornecimento fornecimento) {
        return fornecimentoDao.insert(fornecimento);
    }

    public int updateFornecimento(Fornecimento fornecimento) {
        return fornecimentoDao.update(fornecimento);
    }

    public int deleteFornecimento(Fornecimento fornecimento) {
        return fornecimentoDao.delete(fornecimento);
    }

    public LiveData<List<Fornecimento>> getFornecimentosLiveData() {
        return fornecimentoDao.getFornecimentosLiveData();
    }

    public LiveData<List<Fornecimento>> getFornecimentosByProductLiveData(long productId) {
        return fornecimentoDao.getFornecimentosByProductLiveData(productId);
    }

    public LiveData<List<Fornecimento>> getFornecimentosBySupplierLiveData(long supplierId) {
        return fornecimentoDao.getFornecimentosBySupplierLiveData(supplierId);
    }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    public List<Fornecimento> getFornecimentos() {
        return fornecimentoDao.getFornecimentos();
    }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    public List<Fornecimento> getFornecimentosByProduct(long productId) {
        return fornecimentoDao.getFornecimentosByProduct(productId);
    }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    public List<Fornecimento> getFornecimentosBySupplier(long supplierId) {
        return fornecimentoDao.getFornecimentosBySupplier(supplierId);
    }

    public Fornecimento getFornecimentoProductSupplier(long productId, long supplierId) {
        return fornecimentoDao.getFornecimentoProductSupplier(productId, supplierId);
    }
}
