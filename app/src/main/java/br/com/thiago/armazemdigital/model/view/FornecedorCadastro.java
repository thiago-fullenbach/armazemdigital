package br.com.thiago.armazemdigital.model.view;

import androidx.room.DatabaseView;
import androidx.room.Ignore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@DatabaseView(value = "SELECT fornecedor.id as supplierId, fornecedor.name as supplierName, COUNT(DISTINCT produto.id) as countProduct FROM fornecedor LEFT JOIN fornecimento ON fornecedor.id = fornecimento.supplierId INNER JOIN produto ON produto.id = fornecimento.productId GROUP BY fornecedor.id, produto.id", viewName = "fornecedorCadastro")
public class FornecedorCadastro {
    private long supplierId;
    private String supplierName;
    private int countProduct;

    @Ignore
    public FornecedorCadastro(long supplierId, String supplierName, int countProduct) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.countProduct = countProduct;
    }
}
