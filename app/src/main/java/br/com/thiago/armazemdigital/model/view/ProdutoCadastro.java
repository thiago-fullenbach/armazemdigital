package br.com.thiago.armazemdigital.model.view;

import androidx.room.DatabaseView;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@DatabaseView(value = "SELECT produto.id as productId, produto.urlImage, produto.name as nameProduct, produto.description as descProduct, categoria.name as nameCategory, produto.price as priceProduct FROM produto INNER JOIN categoria ON produto.categoryId == categoria.id ORDER BY produto.id", viewName = "produtocadastro")
public class ProdutoCadastro {
    private long productId;
    private String urlImagem;
    private String nameProduct;
    private String descProduct;
    private String nameCategory;
    private Long priceProduct;

    public ProdutoCadastro(long productId, String urlImagem, String nameProduct, String descProduct, String nameCategory, Long priceProduct) {
        this.productId = productId;
        this.urlImagem = urlImagem;
        this.nameProduct = nameProduct;
        this.descProduct = descProduct;
        this.nameCategory = nameCategory;
        this.priceProduct = priceProduct;
    }
}
