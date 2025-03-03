package br.com.thiago.armazemdigital.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import br.com.thiago.armazemdigital.database.dao.CategoriaDao;
import br.com.thiago.armazemdigital.database.dao.FornecedorDao;
import br.com.thiago.armazemdigital.database.dao.FornecimentoDao;
import br.com.thiago.armazemdigital.database.dao.MovimentacaoDao;
import br.com.thiago.armazemdigital.database.dao.ProdutoDao;
import br.com.thiago.armazemdigital.database.dao.view.ProdutoEstoqueDao;
import br.com.thiago.armazemdigital.model.Categoria;
import br.com.thiago.armazemdigital.model.Fornecedor;
import br.com.thiago.armazemdigital.model.Fornecimento;
import br.com.thiago.armazemdigital.model.Movimentacao;
import br.com.thiago.armazemdigital.model.Produto;
import br.com.thiago.armazemdigital.model.view.ProdutoEstoque;

@Database(version = 1, entities = {
        Produto.class,
        Movimentacao.class,
        Fornecedor.class,
        Fornecimento.class,
        Categoria.class
}, views = ProdutoEstoque.class, exportSchema = false)
@TypeConverters(Converters.class)
public abstract class ArmazemDigitalDb extends RoomDatabase {
    public abstract ProdutoDao produtoDao();

    public abstract MovimentacaoDao movimentacaoDao();

    public abstract FornecedorDao fornecedorDao();

    public abstract FornecimentoDao fornecimentoDao();

    public abstract CategoriaDao categoriaDao();

    public abstract ProdutoEstoqueDao produtoEstoqueDao();
}
