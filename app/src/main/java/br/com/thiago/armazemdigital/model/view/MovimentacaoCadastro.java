package br.com.thiago.armazemdigital.model.view;

import androidx.room.DatabaseView;
import androidx.room.Ignore;

import br.com.thiago.armazemdigital.model.enums.MotivoMovimentacao;
import br.com.thiago.armazemdigital.model.enums.StatusMovimentacao;
import br.com.thiago.armazemdigital.model.enums.TipoMovimentacao;
import br.com.thiago.armazemdigital.model.enums.TipoUnidade;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@DatabaseView(value = "SELECT movimentacao.id as movementId, produto.id as productId, produto.urlImage, produto.name as nameProduct, produto.description as descProduct, movimentacao.operatorName, movimentacao.typeMovement as typeMovement, movimentacao.motive, movimentacao.qtt as qttMovement, movimentacao.observation, produto.unit as unidade, movimentacao.status, COALESCE((SELECT SUM(qtt) FROM movimentacao WHERE productId = produto.id AND status = 2 GROUP BY productId), 0) as qttCurrent FROM produto INNER JOIN movimentacao ON produto.id = movimentacao.productId ORDER BY movimentacao.dateMovement", viewName = "movimentacaoCadastro")
public class MovimentacaoCadastro {
    private long movementId;
    private long productId;
    private String urlImagem;
    private String nameProduct;
    private String descProduct;
    private String operatorName;
    private TipoUnidade unidade;
    private TipoMovimentacao typeMovement;
    private MotivoMovimentacao motive;
    private Long qttMovement;
    private Long qttCurrent;
    private StatusMovimentacao status;
    private String observation;

    @Ignore
    public MovimentacaoCadastro(long movementId, long productId, String urlImagem, String nameProduct, String descProduct, String operatorName, TipoUnidade unidade, TipoMovimentacao typeMovement, MotivoMovimentacao motive, Long qttMovement, StatusMovimentacao status, String observation) {
        this.movementId = movementId;
        this.productId = productId;
        this.urlImagem = urlImagem;
        this.nameProduct = nameProduct;
        this.descProduct = descProduct;
        this.operatorName = operatorName;
        this.unidade = unidade;
        this.typeMovement = typeMovement;
        this.motive = motive;
        this.qttMovement = qttMovement;
        this.status = status;
        this.observation = observation;
    }
}
