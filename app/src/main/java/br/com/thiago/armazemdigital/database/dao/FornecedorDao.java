package br.com.thiago.armazemdigital.database.dao;

import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import br.com.thiago.armazemdigital.model.Fornecedor;

@Dao
public interface FornecedorDao extends BaseDao<Fornecedor> {
    @Query("SELECT * FROM fornecedor ORDER BY id DESC LIMIT :limit OFFSET :offset")
    LiveData<List<Fornecedor>> getFornecedoresLiveData(int limit, int offset);

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    @Query("SELECT * FROM fornecedor ORDER BY id DESC LIMIT :limit OFFSET :offset")
    List<Fornecedor> getFornecedores(int limit, int offset);

    @Query("SELECT * FROM fornecedor WHERE id = :id")
    Fornecedor getFornecedor(long id);
}
