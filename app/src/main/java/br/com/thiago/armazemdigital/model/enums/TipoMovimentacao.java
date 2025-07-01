package br.com.thiago.armazemdigital.model.enums;

public enum TipoMovimentacao {
    ENTRADA("Entrada"),
    SAIDA("Saída"),
    AJUSTE("Ajuste");

    private final String displayDesc;

    TipoMovimentacao(String displayDesc) {
        this.displayDesc = displayDesc;
    }

    public String getDisplayDesc() {
        return displayDesc;
    }

    public static TipoMovimentacao fromDisplayDesc(String displayDesc) {
        for (TipoMovimentacao tipoMovimentacao : values()) {
            if (tipoMovimentacao.getDisplayDesc().equalsIgnoreCase(displayDesc)) {
                return tipoMovimentacao;
            }
        }

        return null;
    }
}
