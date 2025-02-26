package br.com.thiago.armazemdigital.database.dao;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import br.com.thiago.armazemdigital.model.Categoria;

@Dao
public interface CategoriaDao extends BaseDao<Categoria> {
    @Query("SELECT * FROM categoria ORDER BY id DESC LIMIT :limit OFFSET :offset")
    List<Categoria> getCategorias(int limit, int offset);
}
