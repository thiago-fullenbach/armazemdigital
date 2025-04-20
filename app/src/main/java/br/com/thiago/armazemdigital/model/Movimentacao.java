package br.com.thiago.armazemdigital.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

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
    private Long productId;
    private String username;
    private Long qtt;
    private TipoMovimentacao typeMovement;
    private String reason;
    private String observation;
    private StatusMovimentacao statusMovimentacao;
    private Date dateMovement;

    @Ignore
    public Movimentacao(@NonNull Long productId, String username, Long qtt, TipoMovimentacao typeMovement, String reason, String observation, StatusMovimentacao statusMovimentacao, Date dateMovement) {
        this.productId = productId;
        this.username = username;
        this.qtt = qtt;
        this.typeMovement = typeMovement;
        this.reason = reason;
        this.observation = observation;
        this.statusMovimentacao = statusMovimentacao;
        this.dateMovement = dateMovement;
    }
}
