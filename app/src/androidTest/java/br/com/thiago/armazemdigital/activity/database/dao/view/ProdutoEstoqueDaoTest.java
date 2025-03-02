package br.com.thiago.armazemdigital.activity.database.dao.view;

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

import br.com.thiago.armazemdigital.activity.utils.TestUtils;
import br.com.thiago.armazemdigital.database.ArmazemDigitalDb;
import br.com.thiago.armazemdigital.database.dao.CategoriaDao;
import br.com.thiago.armazemdigital.database.dao.MovimentacaoDao;
import br.com.thiago.armazemdigital.database.dao.ProdutoDao;
import br.com.thiago.armazemdigital.database.dao.view.ProdutoEstoqueDao;
import br.com.thiago.armazemdigital.model.Categoria;
import br.com.thiago.armazemdigital.model.Movimentacao;
import br.com.thiago.armazemdigital.model.Produto;
import br.com.thiago.armazemdigital.model.view.ProdutoEstoque;

@RunWith(AndroidJUnit4.class)
public class ProdutoEstoqueDaoTest {
    private ArmazemDigitalDb db;
    private CategoriaDao categoriaDao;
    private MovimentacaoDao movimentacaoDao;
    private ProdutoDao produtoDao;
    private ProdutoEstoqueDao produtoEstoqueDao;

    @Before
    public void setUp() throws Exception {
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
        Categoria categoria = TestUtils.getCategoriaForTests();
        categoria.setId(categoriaDao.insert(categoria));

        Produto produto = TestUtils.getProdutoForTests(categoria.getId());
        produto.setId(produtoDao.insert(produto));

        Movimentacao movimentacao = TestUtils.getMovimentacaoForTests(produto.getId());
        movimentacao.setId(movimentacaoDao.insert(movimentacao));

        // Simula entrada
        produto.setQtt(produto.getQtt() + movimentacao.getQtt());
        produtoDao.update(produto);

        List<ProdutoEstoque> produtosEmEstoque = produtoEstoqueDao.getProdutosEstoque(10, 0);

        assertNotNull("Falha ao obter produto em estoque", produtosEmEstoque);
        assertEquals("Falha ao obter produto em estoque", 1, produtosEmEstoque.size());
        assertEquals("Falha ao obter produto em estoque", produto.getUrlImage(), produtosEmEstoque.get(0).getUrlImageProduct());
        assertEquals("Falha ao obter produto em estoque", produto.getName(), produtosEmEstoque.get(0).getNameProduct());
        assertEquals("Falha ao obter produto em estoque", produto.getDescription(), produtosEmEstoque.get(0).getDescProduct());
        assertEquals("Falha ao obter produto em estoque", (Long) 20000L, produtosEmEstoque.get(0).getQtt());
        assertEquals("Falha ao obter produto em estoque", movimentacao.getDateMovement(), produtosEmEstoque.get(0).getDateMovement());
    }

    @After
    public void tearDown() throws Exception {
        db.close();
    }
}
