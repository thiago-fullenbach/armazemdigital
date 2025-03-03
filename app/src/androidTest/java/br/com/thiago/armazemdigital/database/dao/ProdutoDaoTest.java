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
import br.com.thiago.armazemdigital.model.Produto;
import br.com.thiago.armazemdigital.utils.AndroidTestUtils;

@RunWith(AndroidJUnit4.class)
public class ProdutoDaoTest {
    private ArmazemDigitalDb db;
    private CategoriaDao categoriaDao;
    private ProdutoDao produtoDao;

    @Before
    public void setUp() throws Exception {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, ArmazemDigitalDb.class)
                .allowMainThreadQueries()
                .build();
        categoriaDao = db.categoriaDao();
        produtoDao = db.produtoDao();
    }

    @Test
    public void insertAndRetrieve() {
        Categoria categoria = AndroidTestUtils.createCategoriaForTests();
        categoria.setId(categoriaDao.insert(categoria));

        Produto produto = AndroidTestUtils.createProdutoForTests(categoria.getId());
        produto.setId(produtoDao.insert(produto));

        List<Produto> produtosEmBanco = produtoDao.getProdutos(10, 0);
        Produto produtoEmBanco = produtoDao.getProduto(produto.getId());

        assertNotNull(produtoEmBanco);
        assertNotNull(produtosEmBanco);
        assertEquals(produto, produtoEmBanco);
        assertEquals(1, produtosEmBanco.size());
    }

    @Test
    public void insertAndUpdate() {
        Categoria categoria = AndroidTestUtils.createCategoriaForTests();
        categoria.setId(categoriaDao.insert(categoria));

        Produto produto = AndroidTestUtils.createProdutoForTests(categoria.getId());
        produto.setId(produtoDao.insert(produto));

        String urlImagem = "/exemplo/caminho/imagem.png";
        String nome = "Outro nome";
        String descricao = "Outra descrição";
        long preco = 600L;
        long qtd = 20000L;

        produto.setId(produto.getId());
        produto.setUrlImage(urlImagem);
        produto.setName(nome);
        produto.setDescription(descricao);
        produto.setPrice(preco);
        produto.setQtt(qtd);
        produtoDao.update(produto);

        produto = produtoDao.getProduto(produto.getId());

        assertNotNull(produto);
        assertEquals(urlImagem, produto.getUrlImage());
        assertEquals(nome, produto.getName());
        assertEquals(descricao, produto.getDescription());
        assertEquals((Long) preco, produto.getPrice());
        assertEquals((Long) qtd, produto.getQtt());
    }

    @Test
    public void insertAndDelete() {
        Categoria categoria = AndroidTestUtils.createCategoriaForTests();
        categoria.setId(categoriaDao.insert(categoria));

        Produto produto = AndroidTestUtils.createProdutoForTests(categoria.getId());
        produto.setId(produtoDao.insert(produto));

        produto = produtoDao.getProduto(produto.getId());

        assertNotNull(produto);

        produtoDao.delete(produto);

        produto = produtoDao.getProduto(produto.getId());

        assertNull(produto);
    }

    @After
    public void tearDown() throws Exception {
        db.close();
    }
}
