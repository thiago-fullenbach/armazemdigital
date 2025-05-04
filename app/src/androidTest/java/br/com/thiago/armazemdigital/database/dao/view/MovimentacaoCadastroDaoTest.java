package br.com.thiago.armazemdigital.database.dao.view;

import static org.junit.Assert.assertNotNull;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Assert;
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
import br.com.thiago.armazemdigital.model.view.MovimentacaoCadastro;
import br.com.thiago.armazemdigital.utils.AndroidTestUtils;

@RunWith(AndroidJUnit4.class)
public class MovimentacaoCadastroDaoTest {
    private ArmazemDigitalDb db;
    private CategoriaDao categoriaDao;
    private ProdutoDao produtoDao;
    private MovimentacaoDao movimentacaoDao;
    private MovimentacaoCadastroDao movimentacaoCadastroDao;

    @Before
    public void setUp() throws Exception {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, ArmazemDigitalDb.class)
                .allowMainThreadQueries()
                .build();
        categoriaDao = db.categoriaDao();
        produtoDao = db.produtoDao();
        movimentacaoDao = db.movimentacaoDao();
        movimentacaoCadastroDao = db.movimentacaoCadastroDao();
    }

    @Test
    public void retrieve() {
        Categoria categoria = AndroidTestUtils.createCategoriaForTests();
        categoria.setId(categoriaDao.insert(categoria));

        Produto produto = AndroidTestUtils.createProdutoForTests(categoria.getId());
        produto.setId(produtoDao.insert(produto));

        Movimentacao movimentacao = AndroidTestUtils.createMovimentacaoForTests(produto.getId());
        movimentacao.setId(movimentacaoDao.insert(movimentacao));

        List<MovimentacaoCadastro> movimentacoesCadastro = movimentacaoCadastroDao.getMovimentacoesCadastro();

        assertNotNull(movimentacao);
        Assert.assertEquals(1, movimentacoesCadastro.size());
        Assert.assertEquals(movimentacao.getId(), (Long) movimentacoesCadastro.get(0).getMovementId());
        Assert.assertEquals(movimentacao.getOperatorName(), movimentacoesCadastro.get(0).getOperatorName());
        Assert.assertEquals(movimentacao.getTypeMovement(), movimentacoesCadastro.get(0).getTypeMovement());
        Assert.assertEquals(movimentacao.getMotive(), movimentacoesCadastro.get(0).getMotive());
        Assert.assertEquals(movimentacao.getQtt(), movimentacoesCadastro.get(0).getQttMovement());
        Assert.assertEquals(movimentacao.getStatus(), movimentacoesCadastro.get(0).getStatus());
        Assert.assertEquals(movimentacao.getObservation(), movimentacoesCadastro.get(0).getObservation());
        Assert.assertEquals(produto.getId(), (Long) movimentacoesCadastro.get(0).getProductId());
        Assert.assertEquals(produto.getUrlImage(), movimentacoesCadastro.get(0).getUrlImagem());
        Assert.assertEquals(produto.getName(), movimentacoesCadastro.get(0).getNameProduct());
        Assert.assertEquals(produto.getDescription(), movimentacoesCadastro.get(0).getDescProduct());
    }

    @After
    public void tearDown() throws Exception {
        db.close();
    }
}
