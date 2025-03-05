package br.com.thiago.armazemdigital.utils;

/**
 * Utilitário para manipulação de strings
 */
public class StringUtil {
    /** Verifica se uma string passada é nula ou vazia.
     *
     * @param str String a ser verificada.
     * @return Booleano indicando se a string é vazia ou nula.
     */
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    /** Verifica se um charSequence é nula ou vazia.
     * Se não for nula, transforma em String e utiliza isNullOrEmpty de Strings.
     * { @see StringUtil#isNullOrEmpty(string) }
     *
     * @param charSequence CharSequence a ser verificada.
     * @return Booleano indicando se o charSequence é vazio ou nulo.
     */
    public static boolean isNullOrEmpty(CharSequence charSequence) {
        return charSequence == null || isNullOrEmpty(charSequence.toString());
    }
}
