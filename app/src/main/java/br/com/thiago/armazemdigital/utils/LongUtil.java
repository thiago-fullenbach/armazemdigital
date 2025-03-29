package br.com.thiago.armazemdigital.utils;

/**
 * Utilitário para manipulação de Longs
 */
public class LongUtil {
    /** Transforma o objeto Long em um valor long primitivo.
     *
     * @param longObj Objeto Long a ser transformado.
     * @return Valor long primitivo.
     */
    public static long unbox(Long longObj) {
        return longObj == null ? 0L : longObj;
    }
}
