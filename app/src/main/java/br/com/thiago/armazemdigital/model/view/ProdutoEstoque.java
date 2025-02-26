package br.com.thiago.armazemdigital.model.view;

import androidx.room.DatabaseView;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@DatabaseView(value = "SELECT produto.urlImage as urlImageProduct, produto.name as nameProduct, produto.description as descProduct, movimentacao.dateMovement as dateLastMovement, produto.qtt FROM produto INNER JOIN movimentacao ON produto.id = movimentacao.productId ORDER BY dateMovement DESC", viewName = "produto_estoque")
public class ProdutoEstoque {
    private String urlImageProduct;
    private String nameProduct;
    private String descProduct;
    private Date dateLastMovement;
    private Long qtt;
}
