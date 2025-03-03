package br.com.thiago.armazemdigital.utils;

public class IntegerUtil {
    public static int unboxInteger(Integer integer) {
        if(integer != null)
            return integer;

        return 0;
    }
}
