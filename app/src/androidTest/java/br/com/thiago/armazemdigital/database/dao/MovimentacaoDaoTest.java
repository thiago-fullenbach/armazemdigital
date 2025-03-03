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
import br.com.thiago.armazemdigital.model.Movimentacao;
import br.com.thiago.armazemdigital.model.Produto;
import br.com.thiago.armazemdigital.model.enums.TipoMovimentacao;
import br.com.thiago.armazemdigital.utils.AndroidTestUtils;

@RunWith(AndroidJUnit4.class)
public class MovimentacaoDaoTest {
    private ArmazemDigitalDb db;
    private CategoriaDao categoriaDao;
    private MovimentacaoDao movimentacaoDao;
    private ProdutoDao produtoDao;

    @Before
    public void setUp() throws Exception {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, ArmazemDigitalDb.class)
                .allowMainThreadQueries()
                .build();
        categoriaDao = db.categoriaDao();
        movimentacaoDao = db.movimentacaoDao();
        produtoDao = db.produtoDao();
    }

    @Test
    public void insertAndRetrieve() {
        Categoria categoria = AndroidTestUtils.createCategoriaForTests();
        categoria.setId(categoriaDao.insert(categoria));

        Produto produto = AndroidTestUtils.createProdutoForTests(categoria.getId());
        produto.setId(produtoDao.insert(produto));

        Movimentacao movimentacao = AndroidTestUtils.createMovimentacaoForTests(produto.getId());
        movimentacao.setId(movimentacaoDao.insert(movimentacao));

        List<Movimentacao> movimentacoesBanco = movimentacaoDao.getMovimentacoes(10, 0);
        Movimentacao movimentacaoBanco = movimentacaoDao.getMovimentacao(movimentacao.getId());

        assertNotNull(movimentacaoBanco);
        assertNotNull(movimentacoesBanco);
        assertEquals(movimentacao, movimentacaoBanco);
        assertEquals(1, movimentacoesBanco.size());
    }

    @Test
    public void insertAndUpdate() {
        Categoria categoria = AndroidTestUtils.createCategoriaForTests();
        categoria.setId(categoriaDao.insert(categoria));

        Produto produto = AndroidTestUtils.createProdutoForTests(categoria.getId());
        produto.setId(produtoDao.insert(produto));

        Movimentacao movimentacao = AndroidTestUtils.createMovimentacaoForTests(produto.getId());
        movimentacao.setId(movimentacaoDao.insert(movimentacao));

        String nomeUsuario = "Outro usuário";
        long qtd = 50000L;
        String razao = "Outra razão";
        String observacao = "Outra observação";
        TipoMovimentacao tipoMovimentacao = TipoMovimentacao.SAIDA;

        movimentacaoDao.update(movimentacao);

        movimentacao = movimentacaoDao.getMovimentacao(movimentacao.getId());

        movimentacao.setUsername(nomeUsuario);
        movimentacao.setQtt(qtd);
        movimentacao.setReason(razao);
        movimentacao.setObservation(observacao);
        movimentacao.setTypeMovement(tipoMovimentacao);

        assertNotNull(movimentacao);
        assertEquals(nomeUsuario, movimentacao.getUsername());
        assertEquals((Long) qtd, movimentacao.getQtt());
        assertEquals(razao, movimentacao.getReason());
        assertEquals(observacao, movimentacao.getObservation());
        assertEquals(tipoMovimentacao, movimentacao.getTypeMovement());
    }

    @Test
    public void insertAndDelete() {
        Categoria categoria = AndroidTestUtils.createCategoriaForTests();
        categoria.setId(categoriaDao.insert(categoria));

        Produto produto = AndroidTestUtils.createProdutoForTests(categoria.getId());
        produto.setId(produtoDao.insert(produto));

        Movimentacao movimentacao = AndroidTestUtils.createMovimentacaoForTests(produto.getId());
        movimentacao.setId(movimentacaoDao.insert(movimentacao));

        movimentacao = movimentacaoDao.getMovimentacao(movimentacao.getId());

        assertNotNull(produto);

        movimentacaoDao.delete(movimentacao);

        movimentacao = movimentacaoDao.getMovimentacao(movimentacao.getId());

        assertNull(movimentacao);
    }

    @After
    public void tearDown() throws Exception {
        db.close();
    }
}
