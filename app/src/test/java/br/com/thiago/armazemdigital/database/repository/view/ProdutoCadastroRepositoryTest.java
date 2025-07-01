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

import br.com.thiago.armazemdigital.database.dao.view.ProdutoCadastroDao;
import br.com.thiago.armazemdigital.model.view.ProdutoCadastro;
import br.com.thiago.armazemdigital.utils.TestUtils;

@RunWith(MockitoJUnitRunner.class)
public class ProdutoCadastroRepositoryTest {
    @Mock
    private ProdutoCadastroDao produtoCadastroDao;

    @InjectMocks
    private ProdutoCadastroRepository produtoCadastroRepository;

    @Test
    public void getProdutosCadastro() {
        List<ProdutoCadastro> produtosCadastroMock = TestUtils.createProdutosCadastroForTests();

        when(produtoCadastroRepository.getProdutosCadastro()).thenReturn(produtosCadastroMock);

        List<ProdutoCadastro> produtosCadastro = produtoCadastroRepository.getProdutosCadastro();

        assertNotNull(produtosCadastro);
        assertEquals(produtosCadastroMock, produtosCadastro);

        verify(produtoCadastroDao).getProdutosCadastro();
    }
}