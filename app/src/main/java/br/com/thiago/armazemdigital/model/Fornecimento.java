package br.com.thiago.armazemdigital.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Fornecimento {
    private Long productId;
    private Long supplierId;
}
