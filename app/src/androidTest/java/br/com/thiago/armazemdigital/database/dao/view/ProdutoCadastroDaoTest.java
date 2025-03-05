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
import br.com.thiago.armazemdigital.database.dao.ProdutoDao;
import br.com.thiago.armazemdigital.model.Categoria;
import br.com.thiago.armazemdigital.model.Produto;
import br.com.thiago.armazemdigital.model.view.ProdutoCadastro;
import br.com.thiago.armazemdigital.utils.AndroidTestUtils;

@RunWith(AndroidJUnit4.class)
public class ProdutoCadastroDaoTest {
    private ArmazemDigitalDb db;
    private CategoriaDao categoriaDao;
    private ProdutoDao produtoDao;
    private ProdutoCadastroDao produtoCadastroDao;

    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, ArmazemDigitalDb.class)
                .allowMainThreadQueries()
                .build();
        categoriaDao = db.categoriaDao();
        produtoDao = db.produtoDao();
        produtoCadastroDao = db.produtoCadastroDao();
    }

    @Test
    public void retrieve() {
        Categoria categoria = AndroidTestUtils.createCategoriaForTests();
        categoria.setId(categoriaDao.insert(categoria));

        Produto produto = AndroidTestUtils.createProdutoForTests(categoria.getId());
        produto.setId(produtoDao.insert(produto));

        List<ProdutoCadastro> produtoCadastros = produtoCadastroDao.getProdutosCadastro();

        assertNotNull(produtoCadastros);
        assertEquals(1, produtoCadastros.size());
        assertEquals(produto.getUrlImage(), produtoCadastros.get(0).getUrlImagem());
        assertEquals(produto.getName(), produtoCadastros.get(0).getNameProduct());
        assertEquals(produto.getDescription(), produtoCadastros.get(0).getDescProduct());
        assertEquals(categoria.getName(), produtoCadastros.get(0).getNameCategory());
        assertEquals(produto.getPrice(), produtoCadastros.get(0).getPriceProduct());
    }

    @After
    public void tearDown() {
        db.close();
    }
}
