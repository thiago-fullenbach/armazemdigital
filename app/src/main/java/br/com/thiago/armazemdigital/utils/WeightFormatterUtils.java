package br.com.thiago.armazemdigital.utils;

import android.icu.text.DecimalFormat;
import android.icu.text.DecimalFormatSymbols;

import androidx.annotation.Nullable;

import org.jetbrains.annotations.Contract;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;

public final class WeightFormatterUtils {
    private static final int WEIGHT_SCALE = 3;
    private static final int WEIGHT_MULTIPLIER = 1000;

    private WeightFormatterUtils() {}

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

    @Contract("null -> null; !null -> !null")
    public static String weightLongToString(@Nullable Long weightLong) {
        if(weightLong == null) {
            return null;
        }

        return new BigDecimal(LongValidatorUtils.unbox(weightLong))
                .divide(new BigDecimal(WEIGHT_MULTIPLIER), WEIGHT_SCALE, RoundingMode.HALF_EVEN).toString();
    }

    @Nullable
    public static Long weightStringToLong(@Nullable String weightStr) {
        if(StringValidatorUtils.isNullOrEmpty(weightStr)) {
            return null;
        }

        return new BigDecimal(weightStr).multiply(BigDecimal.valueOf(WEIGHT_MULTIPLIER)).longValue();
    }
}
