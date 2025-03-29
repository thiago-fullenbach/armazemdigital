package br.com.thiago.armazemdigital.database.dao.view;

import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import br.com.thiago.armazemdigital.model.view.CategoriaCadastro;

@Dao
public interface CategoriaCadastroDao {
    @Query("SELECT * from categoriacadastro")
    LiveData<List<CategoriaCadastro>> getCategoriasCadastroLiveData();

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    @Query("SELECT * from categoriacadastro")
    List<CategoriaCadastro> getCategoriasCadastro();
}
