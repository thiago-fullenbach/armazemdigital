package br.com.thiago.armazemdigital.database.dao.view;

import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import br.com.thiago.armazemdigital.model.enums.StatusMovimentacao;
import br.com.thiago.armazemdigital.model.view.MovimentacaoCadastro;

@Dao
public interface MovimentacaoCadastroDao {
    @Query("SELECT * FROM movimentacaoCadastro")
    LiveData<List<MovimentacaoCadastro>> getMovimentacoesCadastroLiveData();

    @Query("SELECT * FROM movimentacaoCadastro WHERE status = :status")
    LiveData<List<MovimentacaoCadastro>> getMovimentacoesCadastroWithStatusLiveData(StatusMovimentacao status);

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    @Query("SELECT * FROM movimentacaoCadastro")
    List<MovimentacaoCadastro> getMovimentacoesCadastro();
}
