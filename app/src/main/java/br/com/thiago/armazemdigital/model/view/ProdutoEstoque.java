package br.com.thiago.armazemdigital.model.view;

import androidx.room.DatabaseView;
import androidx.room.Ignore;

import br.com.thiago.armazemdigital.model.enums.TipoUnidade;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@DatabaseView(value = "SELECT produto.id as productId, produto.urlImage as urlImageProduct, produto.name as nameProduct, produto.description as descProduct, COALESCE(SUM(CASE WHEN movimentacao.status = 2 THEN movimentacao.qtt ELSE 0 END), 0) AS qtt, produto.unit as unidade FROM produto LEFT JOIN movimentacao ON produto.id = movimentacao.productId GROUP BY produto.id ORDER BY produto.dateCreated", viewName = "produtoestoque")
public class ProdutoEstoque {
    private Long productId;
    private String urlImageProduct;
    private String nameProduct;
    private String descProduct;
    private Long qtt;
    private TipoUnidade unidade;

    @Ignore
    public ProdutoEstoque(String urlImageProduct, String nameProduct, String descProduct, Long qtt, TipoUnidade unidade) {
        this.urlImageProduct = urlImageProduct;
        this.nameProduct = nameProduct;
        this.descProduct = descProduct;
        this.qtt = qtt;
        this.unidade = unidade;
    }
}
