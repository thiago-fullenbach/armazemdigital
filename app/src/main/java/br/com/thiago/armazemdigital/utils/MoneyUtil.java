package br.com.thiago.armazemdigital.utils;

import android.icu.text.DecimalFormat;
import android.icu.text.DecimalFormatSymbols;

import androidx.annotation.Nullable;

import java.util.Locale;

/**
 * Utilitário para exibição de valores monetários.
 */
public class MoneyUtil {
    /** Formata valor Long como String para ser exibido em telas.
     * Tenha em mente que todos os valores monetários são representados como inteiros dividos por 100.
     *
     * @param money Valor monetário a ser convertido.
     * @return String formatada com duas casas decimais. Retorna "R$ 0,00" se o valor informado for
     * nulo.
     */
    public static String getFormattedMoney(@Nullable Long money) {
        if(money == null) {
            // Retorna Zero, caso money seja nulo
            money = 0L;
        }

        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("pt", "BR"));
        symbols.setCurrencySymbol("R$");
        symbols.setDecimalSeparator(',');

        DecimalFormat moneyFormat = new DecimalFormat("¤ #,##0.00", symbols);

        // Pega valor dividido por 100, pois preco é armazenado multiplicado por 100
        return moneyFormat.format(money / 100.00);
    }
}
