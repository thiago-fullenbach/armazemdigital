package br.com.thiago.armazemdigital.model.view;

import androidx.room.DatabaseView;
import androidx.room.Ignore;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@DatabaseView(value = "SELECT produto.urlImage as urlImageProduct, produto.name as nameProduct, produto.description as descProduct, movimentacao.dateMovement, SUM(movimentacao.qtt) AS qtt FROM produto INNER JOIN movimentacao ON produto.id = movimentacao.productId WHERE movimentacao.status = 2 GROUP BY produto.id ORDER BY dateMovement DESC", viewName = "produtoestoque")
public class ProdutoEstoque {
    private String urlImageProduct;
    private String nameProduct;
    private String descProduct;
    private Date dateMovement;
    private Long qtt;

    @Ignore
    public ProdutoEstoque(String urlImageProduct, String nameProduct, String descProduct, Date dateMovement, Long qtt) {
        this.urlImageProduct = urlImageProduct;
        this.nameProduct = nameProduct;
        this.descProduct = descProduct;
        this.dateMovement = dateMovement;
        this.qtt = qtt;
    }
}
