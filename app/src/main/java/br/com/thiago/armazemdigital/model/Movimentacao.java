package br.com.thiago.armazemdigital.model;

import br.com.thiago.armazemdigital.model.enums.TipoMovimentacao;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Movimentacao {
    private Long id;
    private Long productId;
    private String username;
    private Long qtt;
    private TipoMovimentacao typeMovement;
    private String reason;
    private String observation;
    private String dateMovement;
}
