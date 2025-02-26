package br.com.thiago.armazemdigital.database.repository;

import java.util.List;

import br.com.thiago.armazemdigital.database.dao.FornecimentoDao;
import br.com.thiago.armazemdigital.model.Fornecimento;

public class FornecimentoRepository {
    private final FornecimentoDao fornecimentoDao;

    public FornecimentoRepository(FornecimentoDao fornecimentoDao) {
        this.fornecimentoDao = fornecimentoDao;
    }

    public void insertFornecimento(Fornecimento fornecimento) {
        fornecimentoDao.insert(fornecimento);
    }

    public void updateFornecimento(Fornecimento fornecimento) {
        fornecimentoDao.update(fornecimento);
    }

    public void deleteFornecimento(Fornecimento fornecimento) {
        fornecimentoDao.delete(fornecimento);
    }

    public List<Fornecimento> getFornecimentos() {
        return fornecimentoDao.getFornecimentos();
    }

    public List<Fornecimento> getFornecimentosByProduct(long productId) {
        return fornecimentoDao.getFornecimentosByProduct(productId);
    }

    public List<Fornecimento> getFornecimentosBySupplier(long supplierId) {
        return fornecimentoDao.getFornecimentosBySupplier(supplierId);
    }

    public Fornecimento getFornecimentoProductSupplier(long productId, long supplierId) {
        return fornecimentoDao.getFornecimentoProductSupplier(productId, supplierId);
    }
}
