package br.com.thiago.armazemdigital.utils;

import br.com.thiago.armazemdigital.R;
import br.com.thiago.armazemdigital.model.enums.TipoMovimentacao;

public final class ViewFormatUtils {
    private ViewFormatUtils() {
    }

    /**
     * Função utilizada para retornar cor de texto de acordo com o tipo de movimentação.
     *
     * @param tipoMovimentacao Tipo de movimentação.
     * @return ResId da cor do texto.
     */
    public static int getWeightColorResIdForTypeMovement(TipoMovimentacao tipoMovimentacao) {
        return switch (tipoMovimentacao) {
            case ENTRADA -> R.color.green;
            case SAIDA -> R.color.red;
            default -> R.color.yellow;
        };
    }

    /**
     * Função utilizada para retornar o texto base de acordo com o tipo de movimentação.
     *
     * @param tipoMovimentacao Tipo de movimentação.
     * @return ResId da string de acordo com o tipo de movimentação.
     */
    public static int getWeightFormattedStringResIdForTypeMovement(TipoMovimentacao tipoMovimentacao) {
        return switch (tipoMovimentacao) {
            case ENTRADA -> R.string.placeholder_stock_entry;
            case SAIDA -> R.string.placeholder_stock_out;
            default -> R.string.placeholder_stock_default;
        };
    }
}
