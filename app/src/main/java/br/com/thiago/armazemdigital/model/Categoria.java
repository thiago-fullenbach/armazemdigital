package br.com.thiago.armazemdigital.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(tableName = "categoria")
public class Categoria {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String name;
    private String description;
    private Date dateCreated;
}
