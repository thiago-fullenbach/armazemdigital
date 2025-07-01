package br.com.thiago.armazemdigital.model.enums;

import java.util.Arrays;

public enum MotivoMovimentacao {
    COMPRA("Compra", new TipoMovimentacao[]{ TipoMovimentacao.ENTRADA }),
    DEVOLUCAO_CLIENTE("Devolução do cliente", new TipoMovimentacao[]{ TipoMovimentacao.ENTRADA }),
    PRODUCAO("Produção", new TipoMovimentacao[]{ TipoMovimentacao.ENTRADA }),
    VENDA("Venda", new TipoMovimentacao[]{ TipoMovimentacao.SAIDA }),
    DEVOLUCAO_FORNECEDOR("Devolução para fornecedor", new TipoMovimentacao[]{ TipoMovimentacao.SAIDA }),
    CONSUMO_INTERNO("Consumo interno", new TipoMovimentacao[]{ TipoMovimentacao.SAIDA }),
    TRANSFERENCIA("Transferência", new TipoMovimentacao[]{ TipoMovimentacao.ENTRADA, TipoMovimentacao.SAIDA }),
    AJUSTE_INVENTARIO("Ajuste de inventário", new TipoMovimentacao[]{ TipoMovimentacao.AJUSTE }),
    OUTRO("Outro", new TipoMovimentacao[]{ TipoMovimentacao.ENTRADA, TipoMovimentacao.SAIDA, TipoMovimentacao.AJUSTE });

    private final String displayDesc;
    private final TipoMovimentacao[] typesForMotive;

    MotivoMovimentacao(String displayDesc, TipoMovimentacao[] typesForMotive) {
        this.displayDesc = displayDesc;
        this.typesForMotive = typesForMotive;
    }

    public String getDisplayDesc() {
        return displayDesc;
    }

    public TipoMovimentacao[] getTypesForMotive() {
        return typesForMotive;
    }

    public static MotivoMovimentacao[] getMotivesForType(TipoMovimentacao tipoMovimentacao) {
        return Arrays.stream(MotivoMovimentacao.values())
                .filter(motive -> Arrays.stream(motive.getTypesForMotive()).anyMatch(t -> t == tipoMovimentacao))
                .toArray(MotivoMovimentacao[]::new);
    }

    public static MotivoMovimentacao fromDisplayDesc(String displayDesc) {
        for (MotivoMovimentacao motivoMovimentacao : values()) {
            if (motivoMovimentacao.getDisplayDesc().equalsIgnoreCase(displayDesc)) {
                return motivoMovimentacao;
            }
        }

        return null;
    }
}
