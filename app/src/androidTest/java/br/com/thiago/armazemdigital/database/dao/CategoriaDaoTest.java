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
import br.com.thiago.armazemdigital.utils.AndroidTestUtils;

@RunWith(AndroidJUnit4.class)
public class CategoriaDaoTest {
    private ArmazemDigitalDb db;
    private CategoriaDao categoriaDao;

    @Before
    public void setUp() throws Exception {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, ArmazemDigitalDb.class)
                .allowMainThreadQueries()
                .build();
        categoriaDao = db.categoriaDao();
    }

    /**
     * Testa inserção e obtenção de categorias do banco de dados.
     */
    @Test
    public void insertAndRetrieve() {
        Categoria categoria = AndroidTestUtils.createCategoriaForTests();
        categoria.setId(categoriaDao.insert(categoria));

        List<Categoria> categoriasBanco = categoriaDao.getCategorias(10, 0);
        Categoria categoriaBanco = categoriaDao.getCategoria(categoria.getId());

        assertNotNull(categoriaBanco);
        assertNotNull(categoriasBanco);
        assertEquals(categoria, categoriaBanco);
        assertEquals(1, categoriasBanco.size());
    }

    /**
     * Testa inserção e atualização de categorias do banco de dados.
     */
    @Test
    public void insertAndUpdate() {
        Categoria categoria = AndroidTestUtils.createCategoriaForTests();
        categoria.setId(categoriaDao.insert(categoria));

        categoria = categoriaDao.getCategoria(categoria.getId());

        assertNotNull(categoria);

        String nome = "Outro nome";
        String descricao = "Outra descricao";

        categoria.setName(nome);
        categoria.setDescription(descricao);

        categoriaDao.update(categoria);

        categoria = categoriaDao.getCategoria(categoria.getId());

        assertNotNull(categoria);
        assertEquals(nome, categoria.getName());
        assertEquals(descricao, categoria.getDescription());
    }

    /**
     * Testa inserção e remoção de categorias do banco de dados.
     */
    @Test
    public void insertAndDelete() {
        Categoria categoria = AndroidTestUtils.createCategoriaForTests();
        categoria.setId(categoriaDao.insert(categoria));

        categoria = categoriaDao.getCategoria(categoria.getId());

        assertNotNull(categoria);

        categoriaDao.delete(categoria);

        categoria = categoriaDao.getCategoria(categoria.getId());

        assertNull(categoria);
    }

    @After
    public void tearDown() throws Exception {
        db.close();
    }
}
