package br.com.thiago.armazemdigital.utils;

import org.jetbrains.annotations.Contract;

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
    @Contract(value = "null -> true", pure = true)
    public static <T> boolean isNullOrEmpty(List<T> list) {
        return list == null || list.isEmpty();
    }
}
