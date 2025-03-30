package br.com.thiago.armazemdigital.utils;

import android.icu.text.SimpleDateFormat;

import java.util.Date;
import java.util.Locale;

/**
 * Utilitário para datas.
 */
public class DateUtils {
    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String LOG_DATE_FORMAT = "yyyy-MM-dd_HH:mm:ss";

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
     * @see DateUtils#formatDate(Date, String)
     *
     * @param date Data a ser formatada.
     * @return Data formatada em String.
     */
    public static String formatDate(Date date) {
        return formatDate(date, DEFAULT_DATE_FORMAT);
    }

    /** Função para formatar uma data no formato padrão para logs.
     * @see DateUtils#formatDate(Date, String)
     *
     * @param date Data a ser formatada.
     * @return Data formatada em String.
     */
    public static String formatLogDate(Date date) {
        return formatDate(date, LOG_DATE_FORMAT);
    }
}
