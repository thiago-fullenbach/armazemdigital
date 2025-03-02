package br.com.thiago.armazemdigital.activity.database.dao;

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

import br.com.thiago.armazemdigital.activity.utils.TestUtils;
import br.com.thiago.armazemdigital.database.ArmazemDigitalDb;
import br.com.thiago.armazemdigital.database.dao.CategoriaDao;
import br.com.thiago.armazemdigital.database.dao.FornecedorDao;
import br.com.thiago.armazemdigital.database.dao.FornecimentoDao;
import br.com.thiago.armazemdigital.database.dao.ProdutoDao;
import br.com.thiago.armazemdigital.model.Categoria;
import br.com.thiago.armazemdigital.model.Fornecedor;
import br.com.thiago.armazemdigital.model.Fornecimento;
import br.com.thiago.armazemdigital.model.Produto;

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
        Categoria categoria = TestUtils.getCategoriaForTests();
        categoria.setId(categoriaDao.insert(categoria));

        Produto produto = TestUtils.getProdutoForTests(categoria.getId());
        produto.setId(produtoDao.insert(produto));

        Fornecedor fornecedor = TestUtils.getFornecedorForTests();
        fornecedor.setId(fornecedorDao.insert(fornecedor));

        Fornecimento fornecimento = TestUtils.getFornecimentoForTests(produto.getId(), fornecedor.getId());
        fornecimentoDao.insert(fornecimento);

        List<Fornecimento> fornecimentosBanco = fornecimentoDao.getFornecimentos();
        Fornecimento fornecimentoBanco = fornecimentoDao.getFornecimentoProductSupplier(produto.getId(), fornecedor.getId());

        assertNotNull("Falha ao cadastrar fornecimento", fornecimentoBanco);
        assertNotNull("Falha ao cadastrar fornecimento", fornecimentosBanco);
        assertEquals("Falha ao cadastrar fornecimento", fornecimento, fornecimentoBanco);
        assertEquals("Falha ao cadastrar fornecimento", 1, fornecimentosBanco.size());

        fornecimentosBanco = fornecimentoDao.getFornecimentosByProduct(produto.getId());

        assertEquals("Falha ao cadastrar fornecimento", produto.getId(), fornecimentoBanco.getProductId());
        assertNotNull("Falha ao cadastrar fornecimento", fornecimentosBanco);
        assertEquals("Falha ao cadastrar fornecimento", 1, fornecimentosBanco.size());

        fornecimentosBanco = fornecimentoDao.getFornecimentosBySupplier(fornecedor.getId());

        assertEquals("Falha ao cadastrar fornecimento", fornecedor.getId(), fornecimentoBanco.getSupplierId());
        assertNotNull("Falha ao cadastrar fornecimento", fornecimentosBanco);
        assertEquals("Falha ao cadastrar fornecimento", 1, fornecimentosBanco.size());
    }

    @Test
    public void insertAndDelete() {
        Categoria categoria = TestUtils.getCategoriaForTests();
        categoria.setId(categoriaDao.insert(categoria));

        Produto produto = TestUtils.getProdutoForTests(categoria.getId());
        produto.setId(produtoDao.insert(produto));

        Fornecedor fornecedor = TestUtils.getFornecedorForTests();
        fornecedor.setId(fornecedorDao.insert(fornecedor));

        Fornecimento fornecimento = TestUtils.getFornecimentoForTests(produto.getId(), fornecedor.getId());
        fornecimentoDao.insert(fornecimento);

        fornecimento = fornecimentoDao.getFornecimentoProductSupplier(produto.getId(), fornecedor.getId());

        assertNotNull("Falha ao cadastrar movimentação", fornecimento);

        fornecimentoDao.delete(fornecimento);

        fornecimento = fornecimentoDao.getFornecimentoProductSupplier(produto.getId(), fornecedor.getId());

        assertNull("Falha ao deletar movimentação", fornecimento);
    }

    @After
    public void tearDown() throws Exception {
        db.close();
    }
}
