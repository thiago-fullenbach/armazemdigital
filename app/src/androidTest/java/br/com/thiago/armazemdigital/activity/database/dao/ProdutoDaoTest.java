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
import br.com.thiago.armazemdigital.database.dao.ProdutoDao;
import br.com.thiago.armazemdigital.model.Categoria;
import br.com.thiago.armazemdigital.model.Produto;

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
        Categoria categoria = TestUtils.getCategoriaForTests();
        categoria.setId(categoriaDao.insert(categoria));

        Produto produto = TestUtils.getProdutoForTests(categoria.getId());
        produto.setId(produtoDao.insert(produto));

        List<Produto> produtosEmBanco = produtoDao.getProdutos(10, 0);
        Produto produtoEmBanco = produtoDao.getProduto(produto.getId());

        assertNotNull("Falha ao cadastrar produto", produtoEmBanco);
        assertNotNull("Falha ao cadastrar produto", produtosEmBanco);
        assertEquals("Falha ao cadastrar produto", produto, produtoEmBanco);
        assertEquals("Falha ao cadastrar produto", 1, produtosEmBanco.size());
    }

    @Test
    public void insertAndUpdate() {
        Categoria categoria = TestUtils.getCategoriaForTests();
        categoria.setId(categoriaDao.insert(categoria));

        Produto produto = TestUtils.getProdutoForTests(categoria.getId());
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

        assertNotNull("Falha ao obter produto do banco de dados", produto);
        assertEquals("Falha ao atualizar produto: urls da imagem não batem", urlImagem, produto.getUrlImage());
        assertEquals("Falha ao atualizar produto: nomes não batem", nome, produto.getName());
        assertEquals("Falha ao atualizar produto: descrições não batem", descricao, produto.getDescription());
        assertEquals("Falha ao atualizar produto: preços não batem", (Long) preco, produto.getPrice());
        assertEquals("Falha ao atualizar produto: quantidades não batem", (Long) qtd, produto.getQtt());
    }

    @Test
    public void insertAndDelete() {
        Categoria categoria = TestUtils.getCategoriaForTests();
        categoria.setId(categoriaDao.insert(categoria));

        Produto produto = TestUtils.getProdutoForTests(categoria.getId());
        produto.setId(produtoDao.insert(produto));

        produto = produtoDao.getProduto(produto.getId());

        assertNotNull("Falha ao cadastrar produto", produto);

        produtoDao.delete(produto);

        produto = produtoDao.getProduto(produto.getId());

        assertNull("Falha ao deletar produto", produto);
    }

    @After
    public void tearDown() throws Exception {
        db.close();
    }
}
