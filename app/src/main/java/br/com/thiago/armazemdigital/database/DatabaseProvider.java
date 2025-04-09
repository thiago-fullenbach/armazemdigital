package br.com.thiago.armazemdigital.database;

import android.content.Context;

import androidx.room.Room;

import javax.inject.Singleton;

import br.com.thiago.armazemdigital.R;
import br.com.thiago.armazemdigital.database.repository.CategoriaRepository;
import br.com.thiago.armazemdigital.database.repository.FornecedorRepository;
import br.com.thiago.armazemdigital.database.repository.FornecimentoRepository;
import br.com.thiago.armazemdigital.database.repository.ProdutoRepository;
import br.com.thiago.armazemdigital.database.repository.view.CategoriaCadastroRepository;
import br.com.thiago.armazemdigital.database.repository.view.FornecedorCadastroRepository;
import br.com.thiago.armazemdigital.database.repository.view.ProdutoCadastroRepository;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

/**
 * Provedor de dependências do banco de dados (DAOs, Repositories e Database)
 */
@Module
@InstallIn(SingletonComponent.class)
public class DatabaseProvider {
    /** Fornece instância do banco de dados do app.
     *
     * @param context Contexto do aplicativo para inicialização do banco de dados.
     * @return Instância do banco de dados do app.
     */
    @Provides
    @Singleton
    public static ArmazemDigitalDb provideDatabase(@ApplicationContext Context context) {
        return Room.databaseBuilder(
                context,
                ArmazemDigitalDb.class,
                context.getString(R.string.db_file_name)).build();
    }

    /** Fornece instância do repositório de produtos.
     * @see DatabaseProvider#provideDatabase(Context)
     *
     * @param db Instância do banco de dados do app.
     * @return Instância do repositório de produtos.
     */
    @Provides
    public static ProdutoRepository provideProdutoRepository(ArmazemDigitalDb db) {
        return new ProdutoRepository(db.produtoDao());
    }

    /** Fornece instância do repositório de categorias.
     * @see DatabaseProvider#provideDatabase(Context)
     *
     * @param db Instância do banco de dados do app.
     * @return Instância do repositório de categorias.
     */
    @Provides
    public static CategoriaRepository provideCategoriaRepository(ArmazemDigitalDb db) {
        return new CategoriaRepository(db.categoriaDao());
    }

    /** Fornece instância do repositório de fornecedores.
     * @see DatabaseProvider#provideDatabase(Context)
     *
     * @param db Instância do banco de dados do app.
     * @return Instância do repositório de fornecedores.
     */
    @Provides
    public static FornecedorRepository provideFornecedorRepository(ArmazemDigitalDb db) {
        return new FornecedorRepository(db.fornecedorDao());
    }

    /** Fornece instância do repositório de fornecimentos.
     * @see DatabaseProvider#provideDatabase(Context)
     *
     * @param db Instância do banco de dados do app.
     * @return Instância do repositório de fornecimentos.
     */
    @Provides
    public static FornecimentoRepository provideFornecimentoRepository(ArmazemDigitalDb db) {
        return new FornecimentoRepository(db.fornecimentoDao());
    }

    /** Fornece instância do repositório de cadastro de produtos.
     * @see DatabaseProvider#provideDatabase(Context)
     *
     * @param db Instância do banco de dados do app.
     * @return Instância do repositório de cadastro de produtos.
     */
    @Provides
    public static ProdutoCadastroRepository provideProdutoCadastroRepository(ArmazemDigitalDb db) {
        return new ProdutoCadastroRepository(db.produtoCadastroDao());
    }

    /** Fornece instância do repositório de cadastro de fornecedores.
     * @see DatabaseProvider#provideDatabase(Context)
     *
     * @param db Instância do banco de dados do app.
     * @return Instância do repositório de cadastro de fornecedores.
     */
    @Provides
    public static FornecedorCadastroRepository provideFornecedorCadastroRepository(ArmazemDigitalDb db) {
        return new FornecedorCadastroRepository(db.fornecedorCadastroDao());
    }

    /** Fornece instância do repositório de cadastro de categorias.
     * @see DatabaseProvider#provideDatabase(Context)
     *
     * @param db Instância do banco de dados do app.
     * @return Instância do repositório de cadastro de categorias.
     */
    @Provides
    public static CategoriaCadastroRepository provideCategoriaCadastroRepository(ArmazemDigitalDb db) {
        return new CategoriaCadastroRepository(db.categoriaCadastroDao());
    }
}
