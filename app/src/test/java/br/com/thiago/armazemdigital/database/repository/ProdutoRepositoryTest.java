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

import br.com.thiago.armazemdigital.database.dao.ProdutoDao;
import br.com.thiago.armazemdigital.model.Produto;
import br.com.thiago.armazemdigital.utils.TestUtils;

@RunWith(MockitoJUnitRunner.class)
public class ProdutoRepositoryTest {
    @Mock
    private ProdutoDao produtoDao;

    @InjectMocks
    private ProdutoRepository produtoRepository;

    @Test
    public void insertProduto() {
        final long idMock = 1L;
        Produto produtoMock = TestUtils.createProdutoForTests();
        produtoMock.setId(idMock);

        when(produtoDao.insert(produtoMock)).thenReturn(idMock);

        long produtoId = produtoRepository.insertProduto(produtoMock);
        assertEquals(idMock, produtoId);

        verify(produtoDao).insert(produtoMock);
    }

    @Test
    public void updateProduto() {
        Produto produtoMock = TestUtils.createProdutoForTests();

        produtoRepository.updateProduto(produtoMock);

        verify(produtoDao).update(produtoMock);
    }

    @Test
    public void deleteProduto() {
        Produto produtoMock = TestUtils.createProdutoForTests();

        produtoRepository.deleteProduto(produtoMock);

        verify(produtoDao).delete(produtoMock);
    }

    @Test
    public void getProdutos() {
        List<Produto> produtosMock = TestUtils.createProdutosForTests();

        when(produtoRepository.getProdutos()).thenReturn(produtosMock);

        List<Produto> produtos = produtoRepository.getProdutos();

        assertNotNull(produtos);
        assertEquals(produtosMock, produtos);

        verify(produtoDao).getProdutos();
    }

    @Test
    public void getProduto() {
        final long idMock = 1L;
        Produto produtoMock = TestUtils.createProdutoForTests();
        produtoMock.setId(idMock);

        when(produtoRepository.getProduto(idMock)).thenReturn(produtoMock);

        Produto produto = produtoRepository.getProduto(idMock);

        assertNotNull(produto);
        assertEquals(produtoMock, produto);

        verify(produtoDao).getProduto(idMock);
    }
}
