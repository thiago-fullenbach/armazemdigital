package br.com.thiago.armazemdigital.utils;

import android.icu.text.DecimalFormat;
import android.icu.text.DecimalFormatSymbols;

import androidx.annotation.Nullable;

import org.jetbrains.annotations.Contract;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;

/**
 * Utilitário para formatação e manipulação de peso.
 */
public final class WeightFormatterUtils {
    private static final int WEIGHT_SCALE = 3;
    private static final int WEIGHT_MULTIPLIER = 1000;

    private WeightFormatterUtils() {}

    /**
     * Função para formatar um valor de peso em String.
     *
     * @param weight Valor de quantidade (Long).
     * @return String representando a quantidade formatada (divido por 1000 antes de conversão para String).
     */
    public static String getFormattedWeight(@Nullable Long weight) {
        if(weight == null) {
            // Retorna zero formatado, caso weight seja nulo
            weight = 0L;
        }

        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        DecimalFormat weightFormat = new DecimalFormat("#,##0.000", symbols);

        // Pega valor dividido por 1000, pois quantidade é armazenada multiplicado por 1000
        return weightFormat.format(new BigDecimal(LongValidatorUtils.unbox(weight))
                .divide(new BigDecimal(WEIGHT_MULTIPLIER), WEIGHT_SCALE, RoundingMode.HALF_EVEN));
    }

    /**
     * Função para formatar um valor de peso em String.
     *
     * @param weightLong Valor de quantidade (Long).
     * @return String representando a quantidade (divido por 1000 antes de conversão para String).
     */
    @Contract("null -> null; !null -> !null")
    public static String weightLongToString(@Nullable Long weightLong) {
        if(weightLong == null) {
            return null;
        }

        return new BigDecimal(LongValidatorUtils.unbox(weightLong))
                .divide(new BigDecimal(WEIGHT_MULTIPLIER), WEIGHT_SCALE, RoundingMode.HALF_EVEN).toString();
    }

    /**
     * Função para converter um valor de peso em String para Long.
     *
     * @param weightStr Valor de quantidade (String).
     * @return Long representando a quantidade (multiplicado por 1000).
     */
    @Nullable
    public static Long weightStringToLong(@Nullable String weightStr) {
        if(StringValidatorUtils.isNullOrEmpty(weightStr)) {
            return null;
        }

        return new BigDecimal(weightStr).multiply(BigDecimal.valueOf(WEIGHT_MULTIPLIER)).longValue();
    }
}
