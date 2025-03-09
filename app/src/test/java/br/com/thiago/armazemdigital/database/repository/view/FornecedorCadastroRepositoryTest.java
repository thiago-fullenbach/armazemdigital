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

import br.com.thiago.armazemdigital.database.dao.view.FornecedorCadastroDao;
import br.com.thiago.armazemdigital.model.view.FornecedorCadastro;
import br.com.thiago.armazemdigital.utils.TestUtils;

@RunWith(MockitoJUnitRunner.class)
public class FornecedorCadastroRepositoryTest {
    @Mock
    private FornecedorCadastroDao fornecedorCadastroDao;

    @InjectMocks
    private FornecedorCadastroRepository fornecedorCadastroRepository;

    @Test
    public void getFornecedoresCadastro() {
        List<FornecedorCadastro> fornecedoresCadastroMock = TestUtils.createFornecedoresCadastroForTests();

        when(fornecedorCadastroRepository.getFornecedoresCadastro()).thenReturn(fornecedoresCadastroMock);

        List<FornecedorCadastro> fornecedoresCadastro = fornecedorCadastroRepository.getFornecedoresCadastro();

        assertNotNull(fornecedoresCadastro);
        assertEquals(fornecedoresCadastroMock, fornecedoresCadastro);

        verify(fornecedorCadastroDao).getFornecedoresCadastro();
    }
}
