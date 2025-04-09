package br.com.thiago.armazemdigital.utils;

import android.icu.text.SimpleDateFormat;

import java.util.Date;
import java.util.Locale;

/**
 * Utilitário para datas.
 */
public final class DateFormatterUtils {
    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private DateFormatterUtils() {}

    /** Função para formatar uma data em um formato específico.
     *
     * @param date Data a ser formatada.
     * @param format Formato de data a ser utilizado.
     * @return Data formatada em String.
     */
    private static String formatDate(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        return sdf.format(date);
    }

    /** Função para formatar uma data no formato padrão.
     * @see DateFormatterUtils#formatDate(Date, String)
     *
     * @param date Data a ser formatada.
     * @return Data formatada em String.
     */
    public static String formatDate(Date date) {
        return formatDate(date, DEFAULT_DATE_FORMAT);
    }
}
