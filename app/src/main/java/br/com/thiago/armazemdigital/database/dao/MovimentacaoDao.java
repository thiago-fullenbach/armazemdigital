package br.com.thiago.armazemdigital.database.dao;

import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import br.com.thiago.armazemdigital.model.Movimentacao;

@Dao
public interface MovimentacaoDao extends BaseDao<Movimentacao> {
    @Query("SELECT * FROM movimentacao ORDER BY id DESC")
    LiveData<List<Movimentacao>> getMovimentacoesLiveData();

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    @Query("SELECT * FROM movimentacao ORDER BY id DESC")
    List<Movimentacao> getMovimentacoes();

    @Query("SELECT * FROM movimentacao WHERE id = :id")
    Movimentacao getMovimentacao(long id);
}
