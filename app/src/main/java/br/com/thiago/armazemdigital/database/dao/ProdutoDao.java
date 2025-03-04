package br.com.thiago.armazemdigital.database.dao;

import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import br.com.thiago.armazemdigital.model.Produto;

@Dao
public interface ProdutoDao extends BaseDao<Produto> {
    @Query("SELECT * FROM produto ORDER BY id DESC LIMIT :limit OFFSET :offset")
    LiveData<List<Produto>> getProdutosLiveData(int limit, int offset);

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    @Query("SELECT * FROM produto ORDER BY id DESC LIMIT :limit OFFSET :offset")
    List<Produto> getProdutos(int limit, int offset);

    @Query("SELECT * FROM produto WHERE id = :id")
    Produto getProduto(long id);
}
