package br.com.thiago.armazemdigital.model.view;

import androidx.room.DatabaseView;

import br.com.thiago.armazemdigital.model.enums.StatusMovimentacao;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@DatabaseView(value = "SELECT produto.urlImage, produto.name as nameProduct, produto.description as descProduct, movimentacao.qtt as qttMovement, movimentacao.status FROM produto INNER JOIN movimentacao ON produto.id = movimentacao.productId", viewName = "movimentacaoCadastro")
public class MovimentacaoCadastro {
    private String urlImagem;
    private String nameProduct;
    private String descProduct;
    private Long qttMovement;
    private StatusMovimentacao status;
}
