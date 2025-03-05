package br.com.thiago.armazemdigital.database.dao;

import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import br.com.thiago.armazemdigital.model.Produto;

@Dao
public interface ProdutoDao extends BaseDao<Produto> {
    @Query("SELECT * FROM produto ORDER BY id DESC")
    LiveData<List<Produto>> getProdutosLiveData();

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    @Query("SELECT * FROM produto ORDER BY id DESC")
    List<Produto> getProdutos();

    @Query("SELECT * FROM produto WHERE id = :id")
    Produto getProduto(long id);
}
