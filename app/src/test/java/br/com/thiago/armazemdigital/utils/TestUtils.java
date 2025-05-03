package br.com.thiago.armazemdigital.utils;

import java.util.Date;
import java.util.List;

import br.com.thiago.armazemdigital.model.Categoria;
import br.com.thiago.armazemdigital.model.Fornecedor;
import br.com.thiago.armazemdigital.model.Fornecimento;
import br.com.thiago.armazemdigital.model.Movimentacao;
import br.com.thiago.armazemdigital.model.Produto;
import br.com.thiago.armazemdigital.model.enums.MotivoMovimentacao;
import br.com.thiago.armazemdigital.model.enums.StatusMovimentacao;
import br.com.thiago.armazemdigital.model.enums.TipoMovimentacao;
import br.com.thiago.armazemdigital.model.enums.TipoUnidade;
import br.com.thiago.armazemdigital.model.view.CategoriaCadastro;
import br.com.thiago.armazemdigital.model.view.FornecedorCadastro;
import br.com.thiago.armazemdigital.model.view.ProdutoCadastro;
import br.com.thiago.armazemdigital.model.view.ProdutoEstoque;

/**
 * Classe utilitária para criação de instâncias de classes de teste.
 * Essa classe deve existir apenas para testes unitários.
 * Diferente da AndroidTestUtils, não é necessário adicionar dependências.
 */
public class TestUtils {
    /** Cria um produto mocado para testes.
     *
     * @return Produto com informações preenchidas.
     */
    public static Produto createProdutoForTests() {
        return new Produto(
                1L,
                null,
                "Produto de teste",
                "Produto para utilização em testes",
                TipoUnidade.UNIDADE,
                500L,
                new Date()
        );
    }

    /** Cria uma lista de produtos mocados para testes.
     *
     * @return Lista de produtos com informações preenchidas.
     */
    public static List<Produto> createProdutosForTests() {
        return List.of(
                new Produto(1L, null, "Teste 1", "Produto Teste 1", TipoUnidade.UNIDADE, 500L, new Date()),
                new Produto(1L, null, "Teste 2", "Produto Teste 1", TipoUnidade.UNIDADE, 500L, new Date()),
                new Produto(1L, null, "Teste 3", "Produto Teste 1", TipoUnidade.UNIDADE, 500L, new Date())
        );
    }

    /** Cria uma movimentação mocada para testes.
     *
     * @return Movimentação com informações preenchidas.
     */
    public static Movimentacao createMovimentacaoForTests() {
        return new Movimentacao(
                1L,
                "Teste User",
                10000L,
                TipoMovimentacao.ENTRADA,
                MotivoMovimentacao.COMPRA,
                "Teste",
                StatusMovimentacao.PENDENTE,
                new Date()
        );
    }

    /** Cria uma lista de movimentações mocadas para testes.
     *
     * @return Lista de movimentações com informações preenchidas.
     */
    public static List<Movimentacao> createMovimentacoesForTests() {
        return List.of(
                new Movimentacao(1L, "Usuario 1", 10000L, TipoMovimentacao.ENTRADA, MotivoMovimentacao.COMPRA, "Obs 1", StatusMovimentacao.PENDENTE, new Date()),
                new Movimentacao(2L, "Usuario 2", 10000L, TipoMovimentacao.SAIDA, MotivoMovimentacao.VENDA, "Obs 2", StatusMovimentacao.PENDENTE, new Date()),
                new Movimentacao(3L, "Usuario 3", 10000L, TipoMovimentacao.AJUSTE, MotivoMovimentacao.AJUSTE_INVENTARIO, "Obs 3", StatusMovimentacao.PENDENTE, new Date())
        );
    }

    /** Cria um fornecedor mocado para testes.
     *
     * @return Fornecedor com informações preenchidas.
     */
    public static Fornecedor createFornecedorForTests() {
        return new Fornecedor(
                "Fornecedor Teste",
                "(11) 2222-2222",
                "empresa@teste.com.br",
                "12345-678",
                "São Paulo",
                "SP",
                "Bairro Exemplo",
                "Rua de Exemplo",
                "Casa 3",
                "123",
                new Date()
        );
    }

    /** Cria uma lista de fornecedores mocados para testes.
     *
     * @return Lista de fornecedores com informações preenchidas.
     */
    public static List<Fornecedor> createFornecedoresForTests() {
        return List.of(
                new Fornecedor("Fornecedor Teste 1", "(11) 2222-2222", "empresa@teste.com.br", "12345-678", "São Paulo", "SP", "Bairro Exemplo", "Rua de Exemplo", "Casa 3", "123", new Date()),
                new Fornecedor("Fornecedor Teste 2", "(11) 2222-2222", "empresa@teste.com.br", "12345-678", "São Paulo", "SP", "Bairro Exemplo", "Rua de Exemplo", "Casa 3", "123", new Date()),
                new Fornecedor("Fornecedor Teste 3", "(11) 2222-2222", "empresa@teste.com.br", "12345-678", "São Paulo", "SP", "Bairro Exemplo", "Rua de Exemplo", "Casa 3", "123", new Date())
        );
    }

    /** Cria uma categoria mocada para testes.
     *
     * @return Categoria com informações preenchidas.
     */
    public static Categoria createCategoriaForTests() {
        return new Categoria(
                "Categoria Teste",
                "Categoria utilizada para testes",
                new Date()
        );
    }

    /** Cria uma lista de categorias mocadas para testes.
     *
     * @return Lista de categorias com informações preenchidas.
     */
    public static List<Categoria> createCategoriasForTests() {
        return List.of(
                new Categoria("Categoria Teste 1", "Categoria utilizada para testes", new Date()),
                new Categoria("Categoria Teste 2", "Categoria utilizada para testes", new Date()),
                new Categoria("Categoria Teste 3", "Categoria utilizada para testes", new Date())
        );
    }

    /** Cria um fornecimento mocado para testes.
     *
     * @return Fornecimento com informações preenchidas.
     */
    public static Fornecimento createFornecimentoForTests() {
        return new Fornecimento(
                1L,
                1L
        );
    }

    /** Cria uma lista de fornecimentos mocados para testes.
     *
     * @return Lista de fornecimentos com informações preenchidas.
     */
    public static List<Fornecimento> createFornecimentosForTests() {
        return List.of(
                new Fornecimento(1L, 1L),
                new Fornecimento(2L, 2L),
                new Fornecimento(3L, 3L)
        );
    }

    /** Cria uma lista de produto em estoque mocados para testes.
     *
     * @return Lista de produtos em estoque com informações preenchidas.
     */
    public static List<ProdutoEstoque> createProdutosEstoqueForTests() {
        return List.of(
                new ProdutoEstoque(null, "Produto Teste 1", "Produto Teste", 500L, TipoUnidade.UNIDADE),
                new ProdutoEstoque(null, "Produto Teste 2", "Produto Teste", 500L, TipoUnidade.UNIDADE),
                new ProdutoEstoque(null, "Produto Teste 3", "Produto Teste", 500L, TipoUnidade.UNIDADE)
        );
    }

    /** Cria uma lista de produtos cadastrados para testes.
     *
     * @return Lista de produtos cadastrados com informações preenchidas.
     */
    public static List<ProdutoCadastro> createProdutosCadastroForTests() {
        return List.of(
                new ProdutoCadastro(1, null, "Produto Teste 1", "Produto Teste", "Categoria Teste", 500L),
                new ProdutoCadastro(2, null, "Produto Teste 2", "Produto Teste", "Categoria Teste", 500L),
                new ProdutoCadastro(3, null, "Produto Teste 3", "Produto Teste", "Categoria Teste", 500L)
        );
    }

    /** Cria uma lista de categorias cadastradas para testes.
     *
     * @return Lista de categorias cadastradas com informações preenchidas.
     */
    public static List<CategoriaCadastro> createCategoriasCadastroForTests() {
        return List.of(
                new CategoriaCadastro(1, "Categoria 1", "Categoria Teste", 1),
                new CategoriaCadastro(2, "Categoria 2", "Categoria Teste", 1),
                new CategoriaCadastro(3, "Categoria 3", "Categoria Teste", 1)
        );
    }

    /** Cria uma lista de fornecedores cadastrados para testes.
     *
     * @return Lista de fornecedores cadastrados com informações preenchidas.
     */
    public static List<FornecedorCadastro> createFornecedoresCadastroForTests() {
        return List.of(
                new FornecedorCadastro(1, "Fornecedor 1", 1),
                new FornecedorCadastro(2, "Fornecedor 2", 1),
                new FornecedorCadastro(3, "Fornecedor 3", 1)
        );
    }
}
