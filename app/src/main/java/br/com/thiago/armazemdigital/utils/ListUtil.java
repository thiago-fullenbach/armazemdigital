package br.com.thiago.armazemdigital.utils;

import java.util.List;

public class ListUtil {
    public static <T> boolean isNullOrEmpty(List<T> list) {
        return list == null || list.isEmpty();
    }
}
