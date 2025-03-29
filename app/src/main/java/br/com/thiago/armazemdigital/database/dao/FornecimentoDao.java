package br.com.thiago.armazemdigital.database.dao;

import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import br.com.thiago.armazemdigital.model.Fornecimento;

@Dao
public interface FornecimentoDao extends BaseDao<Fornecimento> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFornecimentos(List<Fornecimento> fornecimentos);

    @Delete
    void deleteFornecimentos(List<Fornecimento> fornecimentos);

    @Query("SELECT * FROM fornecimento")
    LiveData<List<Fornecimento>> getFornecimentosLiveData();

    @Query("SELECT * FROM fornecimento WHERE productId = :productId")
    LiveData<List<Fornecimento>> getFornecimentosByProductLiveData(long productId);

    @Query("SELECT * FROM fornecimento WHERE supplierId = :supplierId")
    LiveData<List<Fornecimento>> getFornecimentosBySupplierLiveData(long supplierId);

    @Query("SELECT * FROM fornecimento WHERE productId = :productId")
    List<Fornecimento> getCurrentFornecimentosForProduct(long productId);

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
