package br.com.thiago.armazemdigital.utils;

import android.text.Editable;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.Contract;

/**
 * Utilitário para manipulação de strings
 */
public final class StringValidatorUtils {

    private StringValidatorUtils() {}

    /** Verifica se uma string passada é nula ou vazia.
     *
     * @param str String a ser verificada.
     * @return Booleano indicando se a string é vazia ou nula.
     */
    @Contract("null -> true")
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    /** Verifica se um charSequence é nula ou vazia.
     * Se não for nula, transforma em String e utiliza isNullOrEmpty de Strings.
     * @see StringValidatorUtils#isNullOrEmpty(String)
     *
     * @param charSequence CharSequence a ser verificada.
     * @return Booleano indicando se o charSequence é vazio ou nulo.
     */
    @Contract("null -> true")
    public static boolean isNullOrEmpty(CharSequence charSequence) {
        return charSequence == null || isNullOrEmpty(charSequence.toString());
    }

    /** Obtém uma String de um CharSequence, considerando valores nulos.
     *
     * @param charSequence CharSequence obtido de um EditText ou similares.
     * @return toString do charSequence ou String vazia, se for nulo.
     */
    @NonNull
    public static String getSafeStringFromCharSequence(CharSequence charSequence) {
        return isNullOrEmpty(charSequence) ? "" : charSequence.toString();
    }

    /** Obtém uma String de um Editable, considerando valores nulos.
     *
     * @param editable Editable obtido de um EditText ou similares
     * @return toString do editable ou String vazia, se for nulo.
     */
    @NonNull
    public static String getSafeStringFromEditable(Editable editable) {
        return editable == null ? "" : editable.toString();
    }
}
