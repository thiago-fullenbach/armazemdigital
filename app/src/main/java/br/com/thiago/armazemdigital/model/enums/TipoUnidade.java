package br.com.thiago.armazemdigital.model.enums;

import lombok.Getter;

@Getter
public enum TipoUnidade {
    UNIDADE(1, "Unidade", "un"),
    QUILOGRAMA(2, "Quilograma", "kg"),
    GRAMA(3, "Grama", "g"),
    LITRO(4, "Litro", "l"),
    MILILITRO(5, "Mililitro", "ml"),
    METRO(6, "Metro", "m"),
    CENTIMETRO(7, "Centímetro", "cm"),
    MILIMETRO(8, "Milímetro", "mm"),
    PACOTE(9, "Pacote", "pkg");

    private final int code;

    private final String name;

    private final String acronym;

    TipoUnidade(int code, String name, String acronym) {
        this.code = code;
        this.name = name;
        this.acronym = acronym;
    }

    public static TipoUnidade fromCode(int code) {
        for (TipoUnidade tipoUnidade : values()) {
            if (tipoUnidade.getCode() == code) {
                return tipoUnidade;
            }
        }

        return null;
    }

    public static TipoUnidade fromName(String name) {
        for (TipoUnidade tipoUnidade : values()) {
            if (tipoUnidade.getName().equalsIgnoreCase(name)) {
                return tipoUnidade;
            }
        }

        return null;
    }
}
