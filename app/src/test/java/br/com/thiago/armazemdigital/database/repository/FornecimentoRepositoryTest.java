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
import java.util.stream.Collectors;

import br.com.thiago.armazemdigital.database.dao.FornecimentoDao;
import br.com.thiago.armazemdigital.model.Fornecimento;
import br.com.thiago.armazemdigital.utils.TestUtils;

@RunWith(MockitoJUnitRunner.class)
public class FornecimentoRepositoryTest {
    @Mock
    private FornecimentoDao fornecimentoDao;

    @InjectMocks
    private FornecimentoRepository fornecimentoRepository;

    @Test
    public void insertFornecimento() {
        Fornecimento fornecimentoMock = TestUtils.createFornecimentoForTests();

        fornecimentoRepository.insertFornecimento(fornecimentoMock);

        verify(fornecimentoDao).insert(fornecimentoMock);
    }

    @Test
    public void updateFornecimento() {
        Fornecimento fornecimentoMock = TestUtils.createFornecimentoForTests();

        fornecimentoRepository.updateFornecimento(fornecimentoMock);

        verify(fornecimentoDao).update(fornecimentoMock);
    }

    @Test
    public void deleteFornecimento() {
        Fornecimento fornecimentoMock = TestUtils.createFornecimentoForTests();

        fornecimentoRepository.deleteFornecimento(fornecimentoMock);

        verify(fornecimentoDao).delete(fornecimentoMock);
    }

    @Test
    public void getFornecimentos() {
        List<Fornecimento> fornecimentosMock = TestUtils.createFornecimentosForTests();

        when(fornecimentoRepository.getFornecimentos()).thenReturn(fornecimentosMock);

        List<Fornecimento> fornecimentos = fornecimentoRepository.getFornecimentos();

        assertNotNull(fornecimentos);
        assertEquals(fornecimentosMock, fornecimentos);

        verify(fornecimentoDao).getFornecimentos();
    }

    @Test
    public void getFornecimentosByProduct() {
        final long productIdMock = 1L;
        List<Fornecimento> fornecimentosMock = TestUtils.createFornecimentosForTests().stream()
                .filter(fornecimento -> fornecimento.getProductId() == productIdMock)
                .collect(Collectors.toList());

        when(fornecimentoRepository.getFornecimentosByProduct(productIdMock)).thenReturn(fornecimentosMock);

        List<Fornecimento> fornecimentos = fornecimentoRepository.getFornecimentosByProduct(productIdMock);

        assertNotNull(fornecimentos);
        assertEquals(fornecimentosMock, fornecimentos);

        verify(fornecimentoDao).getFornecimentosByProduct(productIdMock);
    }

    @Test
    public void getFornecimentosBySupplier() {
        final long supplierIdMock = 1L;
        List<Fornecimento> fornecimentosMock = TestUtils.createFornecimentosForTests().stream()
                .filter(fornecimento -> fornecimento.getSupplierId() == supplierIdMock)
                .collect(Collectors.toList());

        when(fornecimentoRepository.getFornecimentosBySupplier(supplierIdMock)).thenReturn(fornecimentosMock);

        List<Fornecimento> fornecimentos = fornecimentoRepository.getFornecimentosBySupplier(supplierIdMock);

        assertNotNull(fornecimentos);
        assertEquals(fornecimentosMock, fornecimentos);

        verify(fornecimentoDao).getFornecimentosBySupplier(supplierIdMock);
    }

    @Test
    public void getFornecimentoProductSupplier() {
        final long productIdMock = 1L;
        final long supplierIdMock = 1L;
        Fornecimento fornecimentoMock = TestUtils.createFornecimentoForTests();

        when(fornecimentoRepository.getFornecimentoProductSupplier(productIdMock, supplierIdMock)).thenReturn(fornecimentoMock);

        Fornecimento fornecimento = fornecimentoRepository.getFornecimentoProductSupplier(productIdMock, supplierIdMock);

        assertNotNull(fornecimento);
        assertEquals(fornecimentoMock, fornecimento);

        verify(fornecimentoDao).getFornecimentoProductSupplier(productIdMock, supplierIdMock);
    }
}
