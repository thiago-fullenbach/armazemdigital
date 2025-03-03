package br.com.thiago.armazemdigital.database.dao.view;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import br.com.thiago.armazemdigital.model.view.ProdutoCadastro;

@Dao
public interface ProdutoCadastroDao {
    @Query("SELECT * FROM produtocadastro LIMIT :limit OFFSET :offset")
    List<ProdutoCadastro> getProdutosCadastro(int limit, int offset);
}
