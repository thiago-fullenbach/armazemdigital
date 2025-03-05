package br.com.thiago.armazemdigital.utils;

import android.text.InputFilter;

/**
 * Utilitário para formulários em geral do aplicativo.
 */
public class FormUtils {
    /** Cria um filtro genérico para utilização em TextInputEditText do App.
     *
     * @return InputFilter que permite caracteres alfa-numéricos apenas (inclui cedilha e acentos).
     */
    public static InputFilter getInputFilterForFields() {
        return (charSequence, i, i1, spanned, i2, i3) -> {
            if(charSequence != null && charSequence.length() > 0) {
                return charSequence.toString().matches("^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ0-9 ]+$") ? null : "";
            }

            return null;
        };
    }
}
