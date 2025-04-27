package br.com.thiago.armazemdigital.database.repository.view;

import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;

import java.util.List;

import br.com.thiago.armazemdigital.database.dao.view.MovimentacaoCadastroDao;
import br.com.thiago.armazemdigital.model.enums.StatusMovimentacao;
import br.com.thiago.armazemdigital.model.view.MovimentacaoCadastro;

public class MovimentacaoCadastroRepository {
    private final MovimentacaoCadastroDao movimentacaoCadastroDao;

    public MovimentacaoCadastroRepository(MovimentacaoCadastroDao movimentacaoCadastroDao) {
        this.movimentacaoCadastroDao = movimentacaoCadastroDao;
    }

    public LiveData<List<MovimentacaoCadastro>> getMovimentacoesCadastroLiveData() {
        return movimentacaoCadastroDao.getMovimentacoesCadastroLiveData();
    }

    public LiveData<List<MovimentacaoCadastro>> getMovimentacoesCadastroWithStatusLiveData(StatusMovimentacao status) {
        return movimentacaoCadastroDao.getMovimentacoesCadastroWithStatusLiveData(status);
    }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    public List<MovimentacaoCadastro> getMovimentacoesCadastro() {
        return movimentacaoCadastroDao.getMovimentacoesCadastro();
    }
}
