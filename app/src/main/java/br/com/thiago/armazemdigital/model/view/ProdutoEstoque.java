package br.com.thiago.armazemdigital.model.view;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProdutoEstoque {
    private String nameProduct;
    private String descProduct;
    private Long percDiff;
    private boolean belowMinimal;
    private Long qtt;
}
