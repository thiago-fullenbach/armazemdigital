package br.com.thiago.armazemdigital.model.view;

import androidx.room.DatabaseView;
import androidx.room.Ignore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@DatabaseView(value = "SELECT categoria.id as categoryId, categoria.name as categoryName, categoria.description as categoryDescription, COUNT(DISTINCT produto.id) as productCount FROM categoria LEFT JOIN produto ON produto.categoryId = categoria.id GROUP BY categoria.id, produto.id", viewName = "categoriacadastro")
public class CategoriaCadastro {
    private long categoryId;
    private String categoryName;
    private String categoryDescription;
    private int productCount;

    @Ignore
    public CategoriaCadastro(long categoryId, String categoryName, String categoryDescription, int productCount) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.productCount = productCount;
    }
}
