package br.com.thiago.armazemdigital.utils;

import android.text.InputFilter;

public class FormUtils {
    public static InputFilter getInputFilterForFields() {
        return (charSequence, i, i1, spanned, i2, i3) -> {
            if(charSequence != null && charSequence.length() > 0) {
                return charSequence.toString().matches("^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ0-9 ]+$") ? null : "";
            }

            return null;
        };
    }
}
