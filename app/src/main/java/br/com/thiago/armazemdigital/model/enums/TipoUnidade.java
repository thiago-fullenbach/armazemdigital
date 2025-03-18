package br.com.thiago.armazemdigital.model.enums;

import lombok.Getter;

@Getter
public enum TipoUnidade {
    UNIDADE("Unidade", "un"),
    QUILOGRAMA("Quilograma", "kg"),
    GRAMA("Grama", "g"),
    LITRO("Litro", "l"),
    MILILITRO("Mililitro", "ml"),
    METRO("Metro", "m"),
    CENTIMETRO("Centímetro", "cm"),
    MILIMETRO("Milímetro", "mm"),
    PACOTE("Pacote", "pkg");

    private final String name;

    private final String acronym;

    TipoUnidade(String name, String acronym) {
        this.name = name;
        this.acronym = acronym;
    }
}
