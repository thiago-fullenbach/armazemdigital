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

import br.com.thiago.armazemdigital.database.dao.CategoriaDao;
import br.com.thiago.armazemdigital.model.Categoria;
import br.com.thiago.armazemdigital.utils.TestUtils;

@RunWith(MockitoJUnitRunner.class)
public class CategoriaRepositoryTest {
    @Mock
    private CategoriaDao categoriaDao;

    @InjectMocks
    private CategoriaRepository categoriaRepository;

    @Test
    public void insertCategoria() {
        final long idMock = 1L;
        Categoria categoriaMock = TestUtils.createCategoriaForTests();
        categoriaMock.setId(idMock);

        when(categoriaRepository.insertCategoria(categoriaMock)).thenReturn(idMock);

        long categoriaId = categoriaRepository.insertCategoria(categoriaMock);
        assertEquals(idMock, categoriaId);

        verify(categoriaDao).insert(categoriaMock);
    }

    @Test
    public void updateCategoria() {
        Categoria categoriaMock = TestUtils.createCategoriaForTests();

        categoriaRepository.updateCategoria(categoriaMock);

        verify(categoriaDao).update(categoriaMock);
    }

    @Test
    public void deleteCategoria() {
        Categoria categoriaMock = TestUtils.createCategoriaForTests();

        categoriaRepository.deleteCategoria(categoriaMock);

        verify(categoriaDao).delete(categoriaMock);
    }

    @Test
    public void getCategorias() {
        List<Categoria> categoriasMock = TestUtils.createCategoriasForTests();

        when(categoriaRepository.getCategorias()).thenReturn(categoriasMock);

        List<Categoria> categorias = categoriaRepository.getCategorias();

        assertNotNull(categorias);
        assertEquals(categoriasMock, categorias);

        verify(categoriaDao).getCategorias();
    }

    @Test
    public void getCategoria() {
        final long idMock = 1L;
        Categoria categoriaMock = TestUtils.createCategoriaForTests();
        categoriaMock.setId(idMock);

        when(categoriaRepository.getCategoria(idMock)).thenReturn(categoriaMock);

        Categoria categoria = categoriaRepository.getCategoria(idMock);
        assertNotNull(categoria);
        assertEquals(categoriaMock, categoria);

        verify(categoriaDao).getCategoria(idMock);
    }
}
