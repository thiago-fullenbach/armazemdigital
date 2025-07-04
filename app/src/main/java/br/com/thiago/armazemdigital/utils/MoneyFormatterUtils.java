package br.com.thiago.armazemdigital.utils;

import android.icu.text.DecimalFormat;
import android.icu.text.DecimalFormatSymbols;

import androidx.annotation.Nullable;

import org.jetbrains.annotations.Contract;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;

/**
 * Utilitário para exibição de valores monetários.
 */
public final class MoneyFormatterUtils {
    private static final int MONEY_SCALE = 2;
    private static final int MONEY_MULTIPLIER = 100;

    private MoneyFormatterUtils() {}

    /** Formata valor Long como String para ser exibido em telas.
     * Tenha em mente que todos os valores monetários são representados como inteiros dividos por 100.
     *
     * @param money Valor monetário a ser convertido.
     * @return String formatada com duas casas decimais. Retorna "$ 0.00" se o valor informado for
     * nulo.
     */
    public static String getFormattedMoney(@Nullable Long money) {
        if(money == null) {
            // Retorna zero formatado, caso money seja nulo
            money = 0L;
        }

        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        DecimalFormat moneyFormat = new DecimalFormat("¤ #,##0.00", symbols);

        // Pega valor dividido por 100, pois preco é armazenado multiplicado por 100
        return moneyFormat.format(new BigDecimal(LongValidatorUtils.unbox(money))
                .divide(new BigDecimal(MONEY_MULTIPLIER), MONEY_SCALE, RoundingMode.HALF_EVEN));
    }

    /** Transforma um valor long em uma string representando o valor monetário. Sempre utiliza como
     * separador decimal o '.' e desconsidera símbolo da moeda. O valor long é divido por cem antes
     * de ser formatado.
     *
     * @see MoneyFormatterUtils#moneyStringToLong(String)
     *
     * @param moneyLong Valor monetário a ser convertido.
     * @return String formatada com duas casas decimais. Retorna "" se o valor informado for nulo.
     */
    @Contract("null -> null; !null -> !null")
    public static String moneyLongToString(@Nullable Long moneyLong) {
        if(moneyLong == null) {
            return null;
        }

        return new BigDecimal(LongValidatorUtils.unbox(moneyLong))
                .divide(new BigDecimal(MONEY_MULTIPLIER), MONEY_SCALE, RoundingMode.HALF_EVEN).toString();
    }

    /** Transforma uma string representando o valor monetário em um valor long. O valor convertido
     * é multiplicado por cem antes de ser retornado.
     *
     * @param moneyStr String representando o valor monetário.
     * @return Valor long representando o valor monetário. Retorna null se a string for nula ou vazia.
     */
    @Nullable
    public static Long moneyStringToLong(@Nullable String moneyStr) {
        if(StringValidatorUtils.isNullOrEmpty(moneyStr)) {
            return null;
        }

        return new BigDecimal(moneyStr).multiply(BigDecimal.valueOf(MONEY_MULTIPLIER)).longValue();
    }
}
