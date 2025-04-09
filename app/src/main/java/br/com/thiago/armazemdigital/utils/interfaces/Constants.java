package br.com.thiago.armazemdigital.utils.interfaces;

public interface Constants {
    /**
     * Nome da chave da propriedade para definir o diretório de log.
     */
    String PROPERTY_LOG_DIR_KEY = "LOG_DIR";

    /**
     * Nome do arquivo preferences para armazenar as configurações de log.
     */
    String PREFS_LOG_NAME = "log_prefs";

    /**
     * Nome da chave para armazenar o nível de log no arquivo preferences.
     */
    String KEY_LOG_LEVEL = "log_level";

    /**
     * Nome do appender para registrar logs no arquivo de log.
     */
    String APPENDER_LOGGER_FILE_NAME = "FILE";
}
