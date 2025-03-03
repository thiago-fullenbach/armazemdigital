package br.com.thiago.armazemdigital.database.repository;

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

    public List<Movimentacao> getMovimentacoes(int limit, int offset) {
        return movimentacaoDao.getMovimentacoes(limit, offset);
    }

    public Movimentacao getMovimentacao(long id) {
        return movimentacaoDao.getMovimentacao(id);
    }
}
