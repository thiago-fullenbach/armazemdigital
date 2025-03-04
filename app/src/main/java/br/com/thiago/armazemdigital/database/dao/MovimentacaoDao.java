package br.com.thiago.armazemdigital.database.dao;

import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import br.com.thiago.armazemdigital.model.Movimentacao;

@Dao
public interface MovimentacaoDao extends BaseDao<Movimentacao> {
    @Query("SELECT * FROM movimentacao ORDER BY id DESC LIMIT :limit OFFSET :offset")
    LiveData<List<Movimentacao>> getMovimentacoesLiveData(int limit, int offset);

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    @Query("SELECT * FROM movimentacao ORDER BY id DESC LIMIT :limit OFFSET :offset")
    List<Movimentacao> getMovimentacoes(int limit, int offset);

    @Query("SELECT * FROM movimentacao WHERE id = :id")
    Movimentacao getMovimentacao(long id);
}
