package br.com.thiago.armazemdigital.database.repository;

import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;

import java.util.List;

import br.com.thiago.armazemdigital.database.dao.MovimentacaoDao;
import br.com.thiago.armazemdigital.model.Movimentacao;

public class MovimentacaoRepository {
    private final MovimentacaoDao movimentacaoDao;

    public MovimentacaoRepository(MovimentacaoDao movimentacaoDao) {
        this.movimentacaoDao = movimentacaoDao;
    }

    public long insertMovimentacao(Movimentacao movimentacao) {
        return movimentacaoDao.insert(movimentacao);
    }

    public void updateMovimentacao(Movimentacao movimentacao) {
        movimentacaoDao.update(movimentacao);
    }

    public void deleteMovimentacao(Movimentacao movimentacao) {
        movimentacaoDao.delete(movimentacao);
    }

    public LiveData<List<Movimentacao>> getMovimentacoesLiveData() {
        return movimentacaoDao.getMovimentacoesLiveData();
    }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    public List<Movimentacao> getMovimentacoes() {
        return movimentacaoDao.getMovimentacoes();
    }

    public Movimentacao getMovimentacao(long id) {
        return movimentacaoDao.getMovimentacao(id);
    }
}
