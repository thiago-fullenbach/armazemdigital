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
import br.com.thiago.armazemdigital.database.dao.MovimentacaoDao;
import br.com.thiago.armazemdigital.database.dao.ProdutoDao;
import br.com.thiago.armazemdigital.model.Categoria;
import br.com.thiago.armazemdigital.model.Movimentacao;
import br.com.thiago.armazemdigital.model.Produto;
import br.com.thiago.armazemdigital.model.view.ProdutoEstoque;
import br.com.thiago.armazemdigital.utils.AndroidTestUtils;

@RunWith(AndroidJUnit4.class)
public class ProdutoEstoqueDaoTest {
    private ArmazemDigitalDb db;
    private CategoriaDao categoriaDao;
    private MovimentacaoDao movimentacaoDao;
    private ProdutoDao produtoDao;
    private ProdutoEstoqueDao produtoEstoqueDao;

    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, ArmazemDigitalDb.class)
                .allowMainThreadQueries()
                .build();
        categoriaDao = db.categoriaDao();
        movimentacaoDao = db.movimentacaoDao();
        produtoDao = db.produtoDao();
        produtoEstoqueDao = db.produtoEstoqueDao();
    }

    @Test
    public void retrieve() {
        Categoria categoria = AndroidTestUtils.createCategoriaForTests();
        categoria.setId(categoriaDao.insert(categoria));

        Produto produto = AndroidTestUtils.createProdutoForTests(categoria.getId());
        produto.setId(produtoDao.insert(produto));

        Movimentacao movimentacao = AndroidTestUtils.createMovimentacaoForTests(produto.getId());
        movimentacao.setId(movimentacaoDao.insert(movimentacao));

        List<ProdutoEstoque> produtosEmEstoque = produtoEstoqueDao.getProdutosEstoque();

        assertNotNull(produtosEmEstoque);
        assertEquals(1, produtosEmEstoque.size());
        assertEquals(produto.getUrlImage(), produtosEmEstoque.get(0).getUrlImageProduct());
        assertEquals(produto.getName(), produtosEmEstoque.get(0).getNameProduct());
        assertEquals(produto.getDescription(), produtosEmEstoque.get(0).getDescProduct());
        assertEquals((Long) 10000L, produtosEmEstoque.get(0).getQtt());
        assertEquals(movimentacao.getDateMovement(), produtosEmEstoque.get(0).getDateMovement());
    }

    @After
    public void tearDown() {
        db.close();
    }
}
