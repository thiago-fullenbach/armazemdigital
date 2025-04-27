package br.com.thiago.armazemdigital.model.view;

import androidx.room.DatabaseView;

import br.com.thiago.armazemdigital.model.enums.StatusMovimentacao;
import br.com.thiago.armazemdigital.model.enums.TipoMovimentacao;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@DatabaseView(value = "SELECT movimentacao.id as movementId, produto.urlImage, produto.name as nameProduct, produto.description as descProduct, movimentacao.typeMovement as typeMovement, movimentacao.qtt as qttMovement, movimentacao.status FROM produto INNER JOIN movimentacao ON produto.id = movimentacao.productId", viewName = "movimentacaoCadastro")
public class MovimentacaoCadastro {
    private long movementId;
    private String urlImagem;
    private String nameProduct;
    private String descProduct;
    private TipoMovimentacao typeMovement;
    private Long qttMovement;
    private StatusMovimentacao status;
}
