package br.com.thiago.armazemdigital.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

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
    private Long productId;
    private String username;
    private Long qtt;
    private TipoMovimentacao typeMovement;
    private String reason;
    private String observation;
    private Date dateMovement;
}
