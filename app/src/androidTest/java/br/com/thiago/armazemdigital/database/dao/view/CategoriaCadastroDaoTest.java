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
import br.com.thiago.armazemdigital.model.view.CategoriaCadastro;
import br.com.thiago.armazemdigital.utils.AndroidTestUtils;

@RunWith(AndroidJUnit4.class)
public class CategoriaCadastroDaoTest {
    private ArmazemDigitalDb db;
    private CategoriaDao categoriaDao;
    private CategoriaCadastroDao categoriaCadastroDao;
    private ProdutoDao produtoDao;

    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, ArmazemDigitalDb.class)
                .allowMainThreadQueries()
                .build();
        categoriaDao = db.categoriaDao();
        categoriaCadastroDao = db.categoriaCadastroDao();
        produtoDao = db.produtoDao();
    }

    @Test
    public void retrieve() {
        Categoria categoria = AndroidTestUtils.createCategoriaForTests();
        categoria.setId(categoriaDao.insert(categoria));

        Produto produto = AndroidTestUtils.createProdutoForTests(categoria.getId());
        produto.setId(produtoDao.insert(produto));

        List<CategoriaCadastro> categoriasCadastro = categoriaCadastroDao.getCategoriasCadastro();

        assertNotNull(categoriasCadastro);
        assertEquals(1, categoriasCadastro.size());
        assertEquals((long) categoria.getId(), categoriasCadastro.get(0).getCategoryId());
        assertEquals(categoria.getName(), categoriasCadastro.get(0).getCategoryName());
        assertEquals(categoria.getDescription(), categoriasCadastro.get(0).getCategoryDescription());
        assertEquals(1, categoriasCadastro.get(0).getProductCount());
    }

    @After
    public void tearDown() {
        db.close();
    }
}
