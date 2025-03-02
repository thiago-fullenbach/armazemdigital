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
import br.com.thiago.armazemdigital.database.dao.MovimentacaoDao;
import br.com.thiago.armazemdigital.database.dao.ProdutoDao;
import br.com.thiago.armazemdigital.model.Categoria;
import br.com.thiago.armazemdigital.model.Movimentacao;
import br.com.thiago.armazemdigital.model.Produto;
import br.com.thiago.armazemdigital.model.enums.TipoMovimentacao;

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
        Categoria categoria = TestUtils.getCategoriaForTests();
        categoria.setId(categoriaDao.insert(categoria));

        Produto produto = TestUtils.getProdutoForTests(categoria.getId());
        produto.setId(produtoDao.insert(produto));

        Movimentacao movimentacao = TestUtils.getMovimentacaoForTests(produto.getId());
        movimentacao.setId(movimentacaoDao.insert(movimentacao));

        List<Movimentacao> movimentacoesBanco = movimentacaoDao.getMovimentacoes(10, 0);
        Movimentacao movimentacaoBanco = movimentacaoDao.getMovimentacao(movimentacao.getId());

        assertNotNull("Falha ao cadastrar movimentacao", movimentacaoBanco);
        assertNotNull("Falha ao cadastrar movimentacao", movimentacoesBanco);
        assertEquals("Falha ao cadastrar movimentacao", movimentacao, movimentacaoBanco);
        assertEquals("Falha ao cadastrar movimentacao", 1, movimentacoesBanco.size());
    }

    @Test
    public void insertAndUpdate() {
        Categoria categoria = TestUtils.getCategoriaForTests();
        categoria.setId(categoriaDao.insert(categoria));

        Produto produto = TestUtils.getProdutoForTests(categoria.getId());
        produto.setId(produtoDao.insert(produto));

        Movimentacao movimentacao = TestUtils.getMovimentacaoForTests(produto.getId());
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

        assertNotNull("Falha ao obter movimentação do banco de dados", movimentacao);
        assertEquals("Falha ao atualizar movimentacao: nome de usuário não bate", nomeUsuario, movimentacao.getUsername());
        assertEquals("Falha ao atualizar movimentacao: quantidade não bate", (Long) qtd, movimentacao.getQtt());
        assertEquals("Falha ao atualizar movimentacao: razão não bate", razao, movimentacao.getReason());
        assertEquals("Falha ao atualizar movimentacao: observação não bate", observacao, movimentacao.getObservation());
        assertEquals("Falha ao atualizar movimentacao: tipo de movimentação não bate", tipoMovimentacao, movimentacao.getTypeMovement());
    }

    @Test
    public void insertAndDelete() {
        Categoria categoria = TestUtils.getCategoriaForTests();
        categoria.setId(categoriaDao.insert(categoria));

        Produto produto = TestUtils.getProdutoForTests(categoria.getId());
        produto.setId(produtoDao.insert(produto));

        Movimentacao movimentacao = TestUtils.getMovimentacaoForTests(produto.getId());
        movimentacao.setId(movimentacaoDao.insert(movimentacao));

        movimentacao = movimentacaoDao.getMovimentacao(movimentacao.getId());

        assertNotNull("Falha ao cadastrar movimentação", produto);

        movimentacaoDao.delete(movimentacao);

        movimentacao = movimentacaoDao.getMovimentacao(movimentacao.getId());

        assertNull("Falha ao deletar movimentação", movimentacao);
    }

    @After
    public void tearDown() throws Exception {
        db.close();
    }
}
