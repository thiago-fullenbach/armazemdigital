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

import br.com.thiago.armazemdigital.database.dao.FornecedorDao;
import br.com.thiago.armazemdigital.model.Fornecedor;
import br.com.thiago.armazemdigital.utils.TestUtils;

@RunWith(MockitoJUnitRunner.class)
public class FornecedorRepositoryTest {
    @Mock
    private FornecedorDao fornecedorDao;

    @InjectMocks
    private FornecedorRepository fornecedorRepository;

    @Test
    public void insertFornecedor() {
        final long idMock = 1L;
        Fornecedor fornecedorMock = TestUtils.createFornecedorForTests();
        fornecedorMock.setId(idMock);

        when(fornecedorRepository.insertFornecedor(fornecedorMock)).thenReturn(idMock);

        long fornecedorId = fornecedorRepository.insertFornecedor(fornecedorMock);
        assertEquals(idMock, fornecedorId);

        verify(fornecedorDao).insert(fornecedorMock);
    }

    @Test
    public void updateFornecedor() {
        Fornecedor fornecedorMock = TestUtils.createFornecedorForTests();

        fornecedorRepository.updateFornecedor(fornecedorMock);

        verify(fornecedorDao).update(fornecedorMock);
    }

    @Test
    public void deleteFornecedor() {
        Fornecedor fornecedorMock = TestUtils.createFornecedorForTests();

        fornecedorRepository.deleteFornecedor(fornecedorMock);

        verify(fornecedorDao).delete(fornecedorMock);
    }

    @Test
    public void getFornecedores() {
        List<Fornecedor> fornecedoresMock = TestUtils.createFornecedoresForTests();

        when(fornecedorRepository.getFornecedores()).thenReturn(fornecedoresMock);

        List<Fornecedor> fornecedores = fornecedorRepository.getFornecedores();

        assertNotNull(fornecedores);
        assertEquals(fornecedoresMock, fornecedores);

        verify(fornecedorDao).getFornecedores();
    }
}
