package br.com.thiago.armazemdigital.database.repository.view;

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

import br.com.thiago.armazemdigital.database.dao.view.MovimentacaoCadastroDao;
import br.com.thiago.armazemdigital.model.view.MovimentacaoCadastro;
import br.com.thiago.armazemdigital.utils.TestUtils;

@RunWith(MockitoJUnitRunner.class)
public class MovimentacaoCadastroRepositoryTest {
    @Mock
    private MovimentacaoCadastroDao movimentacaoCadastroDao;

    @InjectMocks
    private MovimentacaoCadastroRepository movimentacaoCadastroRepository;

    @Test
    public void getMovimentacoesCadastro() {
        List<MovimentacaoCadastro> movimentacaoCadastrosMock = TestUtils.createMovimentacoesCadastroForTests();

        when(movimentacaoCadastroRepository.getMovimentacoesCadastro()).thenReturn(movimentacaoCadastrosMock);

        List<MovimentacaoCadastro> movimentacaoCadastros = movimentacaoCadastroRepository.getMovimentacoesCadastro();

        assertNotNull(movimentacaoCadastros);
        assertEquals(movimentacaoCadastrosMock, movimentacaoCadastros);

        verify(movimentacaoCadastroDao).getMovimentacoesCadastro();
    }
}
