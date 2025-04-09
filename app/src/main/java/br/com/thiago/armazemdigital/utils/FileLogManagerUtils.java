package br.com.thiago.armazemdigital.utils;

import static br.com.thiago.armazemdigital.utils.interfaces.Constants.APPENDER_LOGGER_FILE_NAME;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.filter.ThresholdFilter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;

/**
 * Classe de utilitários para log.
 */
public final class FileLogManagerUtils {
    private FileLogManagerUtils() {}

    /** Função para atualizar o nível de log do arquivo de log. Essa alteração não afeta o logcat.
     *
     * @param level String com o nível de log.
     */
    public static void updateFileLogLevel(String level) {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        ch.qos.logback.classic.Logger rootLogger = loggerContext.getLogger(Logger.ROOT_LOGGER_NAME);

        Appender<ILoggingEvent> fileAppender = rootLogger.getAppender(APPENDER_LOGGER_FILE_NAME);

        if (fileAppender != null) {
            fileAppender.clearAllFilters();

            ThresholdFilter filter = new ThresholdFilter();
            filter.setLevel(level);
            filter.start();

            fileAppender.addFilter(filter);
        }
    }
}
