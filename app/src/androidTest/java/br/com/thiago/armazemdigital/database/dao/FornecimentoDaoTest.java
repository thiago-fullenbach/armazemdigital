package br.com.thiago.armazemdigital.database.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import br.com.thiago.armazemdigital.database.ArmazemDigitalDb;
import br.com.thiago.armazemdigital.model.Categoria;
import br.com.thiago.armazemdigital.model.Fornecedor;
import br.com.thiago.armazemdigital.model.Fornecimento;
import br.com.thiago.armazemdigital.model.Produto;
import br.com.thiago.armazemdigital.utils.AndroidTestUtils;

@RunWith(AndroidJUnit4.class)
public class FornecimentoDaoTest {
    private ArmazemDigitalDb db;
    private CategoriaDao categoriaDao;
    private FornecedorDao fornecedorDao;
    private FornecimentoDao fornecimentoDao;
    private ProdutoDao produtoDao;

    @Before
    public void setUp() throws Exception {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, ArmazemDigitalDb.class)
                .allowMainThreadQueries()
                .build();
        categoriaDao = db.categoriaDao();
        fornecedorDao = db.fornecedorDao();
        fornecimentoDao = db.fornecimentoDao();
        produtoDao = db.produtoDao();
    }

    @Test
    public void insertAndRetrieve() {
        Categoria categoria = AndroidTestUtils.createCategoriaForTests();
        categoria.setId(categoriaDao.insert(categoria));

        Produto produto = AndroidTestUtils.createProdutoForTests(categoria.getId());
        produto.setId(produtoDao.insert(produto));

        Fornecedor fornecedor = AndroidTestUtils.createFornecedorForTests();
        fornecedor.setId(fornecedorDao.insert(fornecedor));

        Fornecimento fornecimento = AndroidTestUtils.createFornecimentoForTests(produto.getId(), fornecedor.getId());
        fornecimentoDao.insert(fornecimento);

        List<Fornecimento> fornecimentosBanco = fornecimentoDao.getFornecimentos();
        Fornecimento fornecimentoBanco = fornecimentoDao.getFornecimentoProductSupplier(produto.getId(), fornecedor.getId());

        assertNotNull(fornecimentoBanco);
        assertNotNull(fornecimentosBanco);
        assertEquals(fornecimento, fornecimentoBanco);
        assertEquals(1, fornecimentosBanco.size());

        fornecimentosBanco = fornecimentoDao.getFornecimentosByProduct(produto.getId());

        assertEquals(produto.getId(), fornecimentoBanco.getProductId());
        assertNotNull(fornecimentosBanco);
        assertEquals(1, fornecimentosBanco.size());

        fornecimentosBanco = fornecimentoDao.getFornecimentosBySupplier(fornecedor.getId());

        assertEquals(fornecedor.getId(), fornecimentoBanco.getSupplierId());
        assertNotNull(fornecimentosBanco);
        assertEquals(1, fornecimentosBanco.size());
    }

    @Test
    public void insertAndDelete() {
        Categoria categoria = AndroidTestUtils.createCategoriaForTests();
        categoria.setId(categoriaDao.insert(categoria));

        Produto produto = AndroidTestUtils.createProdutoForTests(categoria.getId());
        produto.setId(produtoDao.insert(produto));

        Fornecedor fornecedor = AndroidTestUtils.createFornecedorForTests();
        fornecedor.setId(fornecedorDao.insert(fornecedor));

        Fornecimento fornecimento = AndroidTestUtils.createFornecimentoForTests(produto.getId(), fornecedor.getId());
        fornecimentoDao.insert(fornecimento);

        fornecimento = fornecimentoDao.getFornecimentoProductSupplier(produto.getId(), fornecedor.getId());

        assertNotNull(fornecimento);

        fornecimentoDao.delete(fornecimento);

        fornecimento = fornecimentoDao.getFornecimentoProductSupplier(produto.getId(), fornecedor.getId());

        assertNull(fornecimento);
    }

    @After
    public void tearDown() throws Exception {
        db.close();
    }
}
