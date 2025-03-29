package br.com.thiago.armazemdigital.database.dao.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
import br.com.thiago.armazemdigital.database.dao.CategoriaDao;
import br.com.thiago.armazemdigital.database.dao.FornecedorDao;
import br.com.thiago.armazemdigital.database.dao.FornecimentoDao;
import br.com.thiago.armazemdigital.database.dao.ProdutoDao;
import br.com.thiago.armazemdigital.model.Categoria;
import br.com.thiago.armazemdigital.model.Fornecedor;
import br.com.thiago.armazemdigital.model.Fornecimento;
import br.com.thiago.armazemdigital.model.Produto;
import br.com.thiago.armazemdigital.model.view.FornecedorCadastro;
import br.com.thiago.armazemdigital.utils.AndroidTestUtils;

@RunWith(AndroidJUnit4.class)
public class FornecedorCadastroDaoTest {
    private ArmazemDigitalDb db;
    private CategoriaDao categoriaDao;
    private FornecedorDao fornecedorDao;
    private FornecimentoDao fornecimentoDao;
    private FornecedorCadastroDao fornecedorCadastroDao;
    private ProdutoDao produtoDao;

    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, ArmazemDigitalDb.class)
                .allowMainThreadQueries()
                .build();
        categoriaDao = db.categoriaDao();
        fornecedorDao = db.fornecedorDao();
        fornecedorCadastroDao = db.fornecedorCadastroDao();
        fornecimentoDao = db.fornecimentoDao();
        produtoDao = db.produtoDao();
    }

    @Test
    public void retrieve() {
        Categoria categoria = AndroidTestUtils.createCategoriaForTests();
        categoria.setId(categoriaDao.insert(categoria));

        Fornecedor fornecedor = AndroidTestUtils.createFornecedorForTests();
        fornecedor.setId(fornecedorDao.insert(fornecedor));

        Produto produto = AndroidTestUtils.createProdutoForTests(categoria.getId());
        produto.setId(produtoDao.insert(produto));

        Fornecimento fornecimento = AndroidTestUtils.createFornecimentoForTests(produto.getId(), fornecedor.getId());
        fornecimentoDao.insert(fornecimento);

        List<FornecedorCadastro> fornecedoresCadastro = fornecedorCadastroDao.getFornecedoresCadastro();

        assertNotNull(fornecedoresCadastro);
        assertEquals(1, fornecedoresCadastro.size());
        assertEquals(1, fornecedoresCadastro.get(0).getCountProduct());
        assertEquals((long) fornecedor.getId(), fornecedoresCadastro.get(0).getSupplierId());
        assertEquals(fornecedor.getName(), fornecedoresCadastro.get(0).getSupplierName());
    }

    @After
    public void tearDown() {
        db.close();
    }
}
