package br.com.thiago.armazemdigital.database.dao;

import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import br.com.thiago.armazemdigital.model.Categoria;

@Dao
public interface CategoriaDao extends BaseDao<Categoria> {
    @Query("SELECT * FROM categoria ORDER BY id")
    LiveData<List<Categoria>> getCategoriasLiveData();

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    @Query("SELECT * FROM categoria ORDER BY id")
    List<Categoria> getCategorias();

    @Query("SELECT * FROM categoria WHERE id = :id")
    Categoria getCategoria(long id);
}
