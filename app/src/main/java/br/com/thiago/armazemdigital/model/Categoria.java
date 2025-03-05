package br.com.thiago.armazemdigital.model;

import androidx.room.Entity;
import androidx.room.Ignore;
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

    @Ignore
    public Categoria(String name, String description, Date dateCreated) {
        this.name = name;
        this.description = description;
        this.dateCreated = dateCreated;
    }

    @Ignore
    public Categoria(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
