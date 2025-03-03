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

import br.com.thiago.armazemdigital.database.dao.view.ProdutoEstoqueDao;
import br.com.thiago.armazemdigital.model.view.ProdutoEstoque;
import br.com.thiago.armazemdigital.utils.TestUtils;

@RunWith(MockitoJUnitRunner.class)
public class ProdutoEstoqueRepositoryTest {
    @Mock
    private ProdutoEstoqueDao produtoEstoqueDao;

    @InjectMocks
    private ProdutoEstoqueRepository produtoEstoqueRepository;

    @Test
    public void getProdutosEstoque() {
        List<ProdutoEstoque> produtosEstoqueMock = TestUtils.createProdutosEstoqueForTests();

        when(produtoEstoqueRepository.getProdutosEstoque(produtosEstoqueMock.size(), 0)).thenReturn(produtosEstoqueMock);

        List<ProdutoEstoque> produtosEstoque = produtoEstoqueRepository.getProdutosEstoque(produtosEstoqueMock.size(), 0);

        assertNotNull(produtosEstoque);
        assertEquals(produtosEstoqueMock, produtosEstoque);

        verify(produtoEstoqueDao).getProdutosEstoque(produtosEstoqueMock.size(), 0);


    }
}
