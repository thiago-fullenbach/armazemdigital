package br.com.thiago.armazemdigital.utils;

public class StringUtil {
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static boolean isNullOrEmpty(CharSequence charSequence) {
        return charSequence == null || isNullOrEmpty(charSequence.toString());
    }
}
