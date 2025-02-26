package br.com.thiago.armazemdigital.database.dao;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import br.com.thiago.armazemdigital.model.Movimentacao;

@Dao
public interface MovimentacaoDao extends BaseDao<Movimentacao> {
    @Query("SELECT * FROM movimentacao ORDER BY id DESC LIMIT :limit OFFSET :offset")
    List<Movimentacao> getMovimentacoes(int limit, int offset);
}
