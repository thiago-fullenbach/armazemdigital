package br.com.thiago.armazemdigital.utils;

import java.util.Date;

import br.com.thiago.armazemdigital.model.Categoria;
import br.com.thiago.armazemdigital.model.Fornecedor;
import br.com.thiago.armazemdigital.model.Fornecimento;
import br.com.thiago.armazemdigital.model.Movimentacao;
import br.com.thiago.armazemdigital.model.Produto;
import br.com.thiago.armazemdigital.model.enums.MotivoMovimentacao;
import br.com.thiago.armazemdigital.model.enums.StatusMovimentacao;
import br.com.thiago.armazemdigital.model.enums.TipoMovimentacao;
import br.com.thiago.armazemdigital.model.enums.TipoUnidade;

/**
 * Classe utilitária para criação de instâncias de classes de teste.
 * Essa classe deve existir apenas para testes do android.
 */
public class AndroidTestUtils {

    /**Cria um produto utilizado para testes automatizados.
     * Uma categoria deve ter sido cadastrada no banco em memória antes da chamada dessa função.
     * @see AndroidTestUtils#createCategoriaForTests()
     *
     * @param categoriaId Id da categoria cadastrada no banco em memória.
     * @return Produto com informações preenchidas.
     */
    public static Produto createProdutoForTests(long categoriaId) {
        return new Produto(
                categoriaId,
                null,
                "Produto Teste",
                "Produto utilizado para testes",
                TipoUnidade.UNIDADE,
                400L,
                new Date()
        );
    }

    /** Cria um fornecedor utilizado para testes automatizados.
     *
     * @return Fornecedor com informações preenchidas.
     */
    public static Fornecedor createFornecedorForTests() {
        return new Fornecedor(
                "Fornecedor Teste",
                "(11) 92222-2222",
                "empresa@teste.com.br",
                "02311-001",
                "São Paulo",
                "SP",
                "Bairro Exemplo",
                "Rua Exemplo",
                "Casa 3",
                "1234",
                new Date()
        );
    }

    /**Cria um fornecimento utilizado para testes automatizados.
     * Um produto e um fornecedor devem ser cadastrados no banco em memória antes da chamada dessa função.
     * @see AndroidTestUtils#createProdutoForTests(long)
     * @see AndroidTestUtils#createFornecedorForTests()
     *
     * @param produtoId ID do produto cadastrado.
     * @param fornecedorId ID do fornecedor cadastrado.
     * @return Fornecimento com informações preenchidas.
     */
    public static Fornecimento createFornecimentoForTests(long produtoId, long fornecedorId) {
        return new Fornecimento(produtoId, fornecedorId);
    }

    /** Cria uma categoria utilizada para testes automatizados.
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

    /** Cria uma movimentação utilizada para testes automatizados.
     * Um produto deve ser cadastrado no banco em memória antes da chamada dessa função.
     * @see AndroidTestUtils#createProdutoForTests(long)
     *
     * @param produtoId ID do produto cadastrado.
     * @return Movimentação com informações preenchidas.
     */
    public static Movimentacao createMovimentacaoForTests(long produtoId) {
        return new Movimentacao(
                produtoId,
                "Thiago",
                10000L,
                TipoMovimentacao.ENTRADA,
                MotivoMovimentacao.COMPRA,
                "Realizado para testes",
                StatusMovimentacao.CONCLUIDO,
                new Date()
        );
    }
}
