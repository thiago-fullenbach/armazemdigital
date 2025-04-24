package br.com.thiago.armazemdigital.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

import br.com.thiago.armazemdigital.model.enums.MotivoMovimentacao;
import br.com.thiago.armazemdigital.model.enums.StatusMovimentacao;
import br.com.thiago.armazemdigital.model.enums.TipoMovimentacao;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(
        tableName = "movimentacao",
        foreignKeys = @ForeignKey(
                entity = Produto.class,
                parentColumns = "id",
                childColumns = "productId",
                onDelete = ForeignKey.CASCADE
        ),
        indices = { @Index("productId") }
)
public class Movimentacao {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    @NonNull
    @lombok.NonNull
    private Long productId;
    private String operatorName;
    private Long qtt;
    private TipoMovimentacao typeMovement;
    private MotivoMovimentacao motive;
    private String observation;
    private StatusMovimentacao status;
    private Date dateMovement;

    @Ignore
    public Movimentacao(@NonNull Long productId, String operatorName, Long qtt, TipoMovimentacao typeMovement, MotivoMovimentacao motive, String observation, StatusMovimentacao status, Date dateMovement) {
        this.productId = productId;
        this.operatorName = operatorName;
        this.qtt = qtt;
        this.typeMovement = typeMovement;
        this.motive = motive;
        this.observation = observation;
        this.status = status;
        this.dateMovement = dateMovement;
    }
}
