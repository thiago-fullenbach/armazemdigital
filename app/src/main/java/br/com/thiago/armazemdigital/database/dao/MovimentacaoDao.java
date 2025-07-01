package br.com.thiago.armazemdigital.database.dao;

import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.thiago.armazemdigital.model.Movimentacao;
import br.com.thiago.armazemdigital.model.enums.StatusMovimentacao;

@Dao
public interface MovimentacaoDao extends BaseDao<Movimentacao> {
    @Update
    int update(List<Movimentacao> movimentacaos);

    @Query("SELECT * FROM movimentacao ORDER BY id DESC")
    LiveData<List<Movimentacao>> getMovimentacoesLiveData();

    @Query("SELECT * FROM movimentacao WHERE status = :status ORDER BY id DESC")
    LiveData<List<Movimentacao>> getMovimentacoesWithStatusLiveData(StatusMovimentacao status);

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    @Query("SELECT * FROM movimentacao ORDER BY id DESC")
    List<Movimentacao> getMovimentacoes();

    @Query("SELECT * FROM movimentacao WHERE id = :id")
    Movimentacao getMovimentacao(long id);
}
