package br.com.thiago.armazemdigital.database.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import br.com.thiago.armazemdigital.database.dao.MovimentacaoDao;
import br.com.thiago.armazemdigital.model.Movimentacao;
import br.com.thiago.armazemdigital.utils.TestUtils;

@RunWith(MockitoJUnitRunner.class)
public class MovimentacaoRepositoryTest {
    @Mock
    private MovimentacaoDao movimentacaoDao;

    @InjectMocks
    private MovimentacaoRepository movimentacaoRepository;

    @Test
    public void insertMovimentacao() {
        final long idMock = 1L;
        Movimentacao movimentacaoMock = TestUtils.createMovimentacaoForTests();
        movimentacaoMock.setId(idMock);

        when(movimentacaoRepository.insertMovimentacao(movimentacaoMock)).thenReturn(idMock);

        long movimentacaoId = movimentacaoRepository.insertMovimentacao(movimentacaoMock);
        assertEquals(idMock, movimentacaoId);

        verify(movimentacaoDao).insert(movimentacaoMock);
    }

    @Test
    public void updateMovimentacao() {
        Movimentacao movimentacaoMock = TestUtils.createMovimentacaoForTests();

        movimentacaoRepository.updateMovimentacao(movimentacaoMock);

        verify(movimentacaoDao).update(movimentacaoMock);
    }

    @Test
    public void deleteMovimentacao() {
        Movimentacao movimentacaoMock = TestUtils.createMovimentacaoForTests();

        movimentacaoRepository.deleteMovimentacao(movimentacaoMock);

        verify(movimentacaoDao).delete(movimentacaoMock);
    }

    @Test
    public void getMovimentacoes() {
        List<Movimentacao> movimentacoesMock = TestUtils.createMovimentacoesForTests();

        when(movimentacaoRepository.getMovimentacoes()).thenReturn(movimentacoesMock);

        List<Movimentacao> movimentacoes = movimentacaoRepository.getMovimentacoes();

        assertNotNull(movimentacoes);
        assertEquals(movimentacoesMock, movimentacoes);

        verify(movimentacaoDao).getMovimentacoes();
    }

    @Test
    public void getMovimentacao() {
        final long idMock = 1L;
        Movimentacao movimentacaoMock = TestUtils.createMovimentacaoForTests();
        movimentacaoMock.setId(idMock);

        when(movimentacaoRepository.getMovimentacao(idMock)).thenReturn(movimentacaoMock);

        Movimentacao movimentacao = movimentacaoRepository.getMovimentacao(idMock);

        assertNotNull(movimentacao);
        assertEquals(movimentacaoMock, movimentacao);

        verify(movimentacaoDao).getMovimentacao(idMock);
    }
}
