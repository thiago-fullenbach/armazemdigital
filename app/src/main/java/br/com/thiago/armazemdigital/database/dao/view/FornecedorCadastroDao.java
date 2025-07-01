package br.com.thiago.armazemdigital.database.dao.view;

import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import br.com.thiago.armazemdigital.model.view.FornecedorCadastro;

@Dao
public interface FornecedorCadastroDao {
    @Query("SELECT * FROM fornecedorCadastro")
    LiveData<List<FornecedorCadastro>> getFornecedoresCadastroLiveData();

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    @Query("SELECT * FROM fornecedorCadastro")
    List<FornecedorCadastro> getFornecedoresCadastro();
}
