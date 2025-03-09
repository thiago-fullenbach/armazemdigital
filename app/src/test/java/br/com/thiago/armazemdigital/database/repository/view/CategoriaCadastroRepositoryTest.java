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

import br.com.thiago.armazemdigital.database.dao.view.CategoriaCadastroDao;
import br.com.thiago.armazemdigital.model.view.CategoriaCadastro;
import br.com.thiago.armazemdigital.utils.TestUtils;

@RunWith(MockitoJUnitRunner.class)
public class CategoriaCadastroRepositoryTest {
    @Mock
    private CategoriaCadastroDao categoriaCadastroDao;

    @InjectMocks
    private CategoriaCadastroRepository categoriaCadastroRepository;

    @Test
    public void getCategoriasCadastro() {
        List<CategoriaCadastro> categoriaCadastrosMock = TestUtils.createCategoriasCadastroForTests();

        when(categoriaCadastroRepository.getCategoriasCadastro()).thenReturn(categoriaCadastrosMock);

        List<CategoriaCadastro> categoriasCadastro = categoriaCadastroRepository.getCategoriasCadastro();

        assertNotNull(categoriasCadastro);
        assertEquals(categoriaCadastrosMock, categoriasCadastro);

        verify(categoriaCadastroDao).getCategoriasCadastro();
    }
}
