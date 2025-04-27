package br.com.thiago.armazemdigital.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import br.com.thiago.armazemdigital.database.dao.CategoriaDao;
import br.com.thiago.armazemdigital.database.dao.FornecedorDao;
import br.com.thiago.armazemdigital.database.dao.FornecimentoDao;
import br.com.thiago.armazemdigital.database.dao.MovimentacaoDao;
import br.com.thiago.armazemdigital.database.dao.ProdutoDao;
import br.com.thiago.armazemdigital.database.dao.view.CategoriaCadastroDao;
import br.com.thiago.armazemdigital.database.dao.view.FornecedorCadastroDao;
import br.com.thiago.armazemdigital.database.dao.view.MovimentacaoCadastroDao;
import br.com.thiago.armazemdigital.database.dao.view.ProdutoCadastroDao;
import br.com.thiago.armazemdigital.database.dao.view.ProdutoEstoqueDao;
import br.com.thiago.armazemdigital.model.Categoria;
import br.com.thiago.armazemdigital.model.Fornecedor;
import br.com.thiago.armazemdigital.model.Fornecimento;
import br.com.thiago.armazemdigital.model.Movimentacao;
import br.com.thiago.armazemdigital.model.Produto;
import br.com.thiago.armazemdigital.model.view.CategoriaCadastro;
import br.com.thiago.armazemdigital.model.view.FornecedorCadastro;
import br.com.thiago.armazemdigital.model.view.MovimentacaoCadastro;
import br.com.thiago.armazemdigital.model.view.ProdutoCadastro;
import br.com.thiago.armazemdigital.model.view.ProdutoEstoque;

@Database(version = 1, entities = {
        Produto.class,
        Movimentacao.class,
        Fornecedor.class,
        Fornecimento.class,
        Categoria.class,
}, views = {
        ProdutoEstoque.class,
        MovimentacaoCadastro.class,
        ProdutoCadastro.class,
        CategoriaCadastro.class,
        FornecedorCadastro.class
}, exportSchema = false)
@TypeConverters(Converters.class)
public abstract class ArmazemDigitalDb extends RoomDatabase {
    public abstract ProdutoDao produtoDao();

    public abstract MovimentacaoDao movimentacaoDao();

    public abstract FornecedorDao fornecedorDao();

    public abstract FornecimentoDao fornecimentoDao();

    public abstract CategoriaDao categoriaDao();

    public abstract ProdutoEstoqueDao produtoEstoqueDao();

    public abstract ProdutoCadastroDao produtoCadastroDao();

    public abstract CategoriaCadastroDao categoriaCadastroDao();

    public abstract FornecedorCadastroDao fornecedorCadastroDao();

    public abstract MovimentacaoCadastroDao movimentacaoCadastroDao();
}
