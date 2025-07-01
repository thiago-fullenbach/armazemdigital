package br.com.thiago.armazemdigital.database.dao;

import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import br.com.thiago.armazemdigital.model.Fornecedor;

@Dao
public interface FornecedorDao extends BaseDao<Fornecedor> {
    @Query("SELECT * FROM fornecedor ORDER BY id DESC")
    LiveData<List<Fornecedor>> getFornecedoresLiveData();

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    @Query("SELECT * FROM fornecedor ORDER BY id DESC")
    List<Fornecedor> getFornecedores();

    @Query("SELECT * FROM fornecedor WHERE id = :id")
    Fornecedor getFornecedor(long id);
}
