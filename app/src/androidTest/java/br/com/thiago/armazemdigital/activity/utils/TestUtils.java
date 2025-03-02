package br.com.thiago.armazemdigital.activity.utils;

import java.util.Date;

import br.com.thiago.armazemdigital.model.Categoria;
import br.com.thiago.armazemdigital.model.Fornecedor;
import br.com.thiago.armazemdigital.model.Fornecimento;
import br.com.thiago.armazemdigital.model.Movimentacao;
import br.com.thiago.armazemdigital.model.Produto;
import br.com.thiago.armazemdigital.model.enums.TipoMovimentacao;

/**
 * Classe utilitária para criação de instâncias de classes de teste.
 * Essa classe deve existir apenas para testes.
 */
public class TestUtils {

    /**Cria um produto utilizado para testes automatizados.
     * Uma categoria deve ter sido cadastrada no banco em memória antes da chamada dessa função.
     * @see TestUtils#getCategoriaForTests()
     *
     * @param categoriaId Id da categoria cadastrada no banco em memória.
     * @return Produto com informações preenchidas.
     */
    public static Produto getProdutoForTests(long categoriaId) {
        return new Produto(
                categoriaId,
                null,
                "Produto Teste",
                "Produto utilizado para testes",
                400L,
                10000L,
                new Date()
        );
    }

    /** Cria um fornecedor utilizado para testes automatizados.
     *
     * @return Fornecedor com informações preenchidas.
     */
    public static Fornecedor getFornecedorForTests() {
        return new Fornecedor(
                "Fornecedor Teste",
                "(11) 92222-2222",
                "empresa@teste.com.br",
                "02311-001",
                "São Paulo",
                "SP",
                "Rua Exemplo",
                "Casa 3",
                "1234",
                new Date()
        );
    }

    /**Cria um fornecimento utilizado para testes automatizados.
     * Um produto e um fornecedor devem ser cadastrados no banco em memória antes da chamada dessa função.
     * @see TestUtils#getProdutoForTests(long)
     * @see TestUtils#getFornecedorForTests()
     *
     * @param produtoId ID do produto cadastrado.
     * @param fornecedorId ID do fornecedor cadastrado.
     * @return Fornecimento com informações preenchidas.
     */
    public static Fornecimento getFornecimentoForTests(long produtoId, long fornecedorId) {
        return new Fornecimento(produtoId, fornecedorId);
    }

    /** Cria uma categoria utilizada para testes automatizados.
     *
     * @return Categoria com informações preenchidas.
     */
    public static Categoria getCategoriaForTests() {
        return new Categoria(
                "Categoria Teste",
                "Categoria utilizada para testes",
                new Date()
        );
    }

    /** Cria uma movimentação utilizada para testes automatizados.
     * Um produto deve ser cadastrado no banco em memória antes da chamada dessa função.
     * @see TestUtils#getProdutoForTests(long)
     *
     * @param produtoId ID do produto cadastrado.
     * @return Movimentação com informações preenchidas.
     */
    public static Movimentacao getMovimentacaoForTests(long produtoId) {
        return new Movimentacao(
                produtoId,
                "Thiago",
                10000L,
                TipoMovimentacao.ENTRADA,
                "Teste",
                "Realizado para testes",
                new Date()
        );
    }
}
