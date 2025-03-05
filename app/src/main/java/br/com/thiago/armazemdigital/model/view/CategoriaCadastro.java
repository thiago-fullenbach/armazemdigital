package br.com.thiago.armazemdigital.model.view;

import androidx.room.DatabaseView;

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
}
