package br.com.thiago.armazemdigital.database.dao;

import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import br.com.thiago.armazemdigital.model.Fornecimento;

@Dao
public interface FornecimentoDao extends BaseDao<Fornecimento> {
    @Query("SELECT * FROM fornecimento")
    LiveData<List<Fornecimento>> getFornecimentosLiveData();

    @Query("SELECT * FROM fornecimento WHERE productId = :productId")
    LiveData<List<Fornecimento>> getFornecimentosByProductLiveData(long productId);

    @Query("SELECT * FROM fornecimento WHERE supplierId = :supplierId")
    LiveData<List<Fornecimento>> getFornecimentosBySupplierLiveData(long supplierId);

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    @Query("SELECT * FROM fornecimento")
    List<Fornecimento> getFornecimentos();

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    @Query("SELECT * FROM fornecimento WHERE productId = :productId")
    List<Fornecimento> getFornecimentosByProduct(long productId);

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    @Query("SELECT * FROM fornecimento WHERE supplierId = :supplierId")
    List<Fornecimento> getFornecimentosBySupplier(long supplierId);

    @Query("SELECT * FROM fornecimento WHERE productId = :productId AND supplierId = :supplierId")
    Fornecimento getFornecimentoProductSupplier(long productId, long supplierId);
}
