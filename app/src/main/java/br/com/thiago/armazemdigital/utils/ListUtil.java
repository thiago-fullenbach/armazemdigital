package br.com.thiago.armazemdigital.utils;

import java.util.List;

/**
 * Utilitário para listas de itens.
 */
public class ListUtil {
    /** Verifica se a lista é nula ou vazia.
     *
     * @param list Lista a ser verificada.
     * @param <T> Tipo genérico da lista (estende Object).
     * @return Booleano indicando se a lista é nula ou vazia.
     */
    public static <T> boolean isNullOrEmpty(List<T> list) {
        return list == null || list.isEmpty();
    }
}
