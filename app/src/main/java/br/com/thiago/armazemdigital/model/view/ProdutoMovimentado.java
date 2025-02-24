package br.com.thiago.armazemdigital.model.view;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProdutoMovimentado {
    private String nameProduct;
    private String descProduct;
    private Long qtt;
}
