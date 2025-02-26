package br.com.thiago.armazemdigital.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

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
    private Long categoryId;
    private String urlImage;
    private String name;
    private String description;
    private Long price;
    private Long qtt;
    private Date dateCreated;
}
