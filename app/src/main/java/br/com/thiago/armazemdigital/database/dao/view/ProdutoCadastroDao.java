package br.com.thiago.armazemdigital.database.dao.view;

import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import br.com.thiago.armazemdigital.model.view.ProdutoCadastro;

@Dao
public interface ProdutoCadastroDao {
    @Query("SELECT * FROM produtocadastro WHERE productId = :id")
    ProdutoCadastro getProdutoCadastro(long id);

    @Query("SELECT * FROM produtocadastro")
    LiveData<List<ProdutoCadastro>> getProdutosCadastroLiveData();

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    @Query("SELECT * FROM produtocadastro")
    List<ProdutoCadastro> getProdutosCadastro();
}
