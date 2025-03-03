package br.com.thiago.armazemdigital.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Entity(
        tableName = "fornecimento",
        primaryKeys = {"productId", "supplierId"},
        foreignKeys = {
                @ForeignKey(
                        entity = Produto.class,
                        parentColumns = "id",
                        childColumns = "productId",
                        onDelete = ForeignKey.RESTRICT
                ),
                @ForeignKey(
                        entity = Fornecedor.class,
                        parentColumns = "id",
                        childColumns = "supplierId",
                        onDelete = ForeignKey.RESTRICT
                )
        },
        indices = {@Index("productId"), @Index("supplierId")}
)
public class Fornecimento {
    @NonNull
    @lombok.NonNull
    private Long productId;
    @NonNull
    @lombok.NonNull
    private Long supplierId;
}
