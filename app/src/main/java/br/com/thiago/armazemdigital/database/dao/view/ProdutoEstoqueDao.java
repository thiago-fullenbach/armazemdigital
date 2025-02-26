package br.com.thiago.armazemdigital.database.dao.view;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import br.com.thiago.armazemdigital.model.view.ProdutoEstoque;

@Dao
public interface ProdutoEstoqueDao {
    @Query("SELECT * FROM produto_estoque LIMIT :limit OFFSET :offset")
    List<ProdutoEstoque> getProdutosEstoque(int limit, int offset);
}
