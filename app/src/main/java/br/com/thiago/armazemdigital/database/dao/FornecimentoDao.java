package br.com.thiago.armazemdigital.database.dao;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import br.com.thiago.armazemdigital.model.Fornecimento;

@Dao
public interface FornecimentoDao extends BaseDao<Fornecimento> {
    @Query("SELECT * FROM fornecimento")
    List<Fornecimento> getFornecimentos();

    @Query("SELECT * FROM fornecimento WHERE productId = :productId")
    List<Fornecimento> getFornecimentosByProduct(long productId);

    @Query("SELECT * FROM fornecimento WHERE supplierId = :supplierId")
    List<Fornecimento> getFornecimentosBySupplier(long supplierId);

    @Query("SELECT * FROM fornecimento WHERE productId = :productId AND supplierId = :supplierId")
    Fornecimento getFornecimentoProductSupplier(long productId, long supplierId);
}
