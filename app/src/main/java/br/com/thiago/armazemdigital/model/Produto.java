package br.com.thiago.armazemdigital.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

import br.com.thiago.armazemdigital.model.enums.TipoUnidade;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(
        tableName = "produto",
        foreignKeys = @ForeignKey(
                entity = Categoria.class,
                parentColumns = "id",
                childColumns = "categoryId",
                onDelete = ForeignKey.RESTRICT
        ),
        indices = { @Index("categoryId") }
)
public class Produto {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    @NonNull
    private Long categoryId;
    private String urlImage;
    private String name;
    private String description;
    private TipoUnidade unit;
    private Long price;
    private Long qtt;
    private Date dateCreated;

    @Ignore
    public Produto(@NonNull Long categoryId, String urlImage, String name, String description, TipoUnidade unit, Long price, Long qtt, Date dateCreated) {
        this.categoryId = categoryId;
        this.urlImage = urlImage;
        this.name = name;
        this.description = description;
        this.unit = unit;
        this.price = price;
        this.qtt = qtt;
        this.dateCreated = dateCreated;
    }
}
