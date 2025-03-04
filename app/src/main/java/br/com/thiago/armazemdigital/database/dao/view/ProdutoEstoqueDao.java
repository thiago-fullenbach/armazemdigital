package br.com.thiago.armazemdigital.database.dao.view;

import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import br.com.thiago.armazemdigital.model.view.ProdutoEstoque;

@Dao
public interface ProdutoEstoqueDao {
    @Query("SELECT * FROM produtoestoque LIMIT :limit OFFSET :offset")
    LiveData<List<ProdutoEstoque>> getProdutosEstoqueLiveData(int limit, int offset);

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    @Query("SELECT * FROM produtoestoque LIMIT :limit OFFSET :offset")
    List<ProdutoEstoque> getProdutosEstoque(int limit, int offset);
}
