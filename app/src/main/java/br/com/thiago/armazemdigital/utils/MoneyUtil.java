package br.com.thiago.armazemdigital.utils;

import android.icu.text.DecimalFormat;
import android.icu.text.DecimalFormatSymbols;

import java.util.Locale;

public class MoneyUtil {
    /**
     * @param money Valor monetário a ser convertido.
     * @return String formatada com duas
     */
    public static String getFormattedMoney(Long money) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setCurrencySymbol("R$");
        symbols.setDecimalSeparator(',');

        DecimalFormat format = new DecimalFormat("¤#.00");

        // Pega valor dividido por 100, pois preco é armazenado multiplicado por 100
        return String.format(format.format(money / 100.00));
    }
}
