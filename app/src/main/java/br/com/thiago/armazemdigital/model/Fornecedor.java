package br.com.thiago.armazemdigital.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(tableName = "fornecedor")
public class Fornecedor {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String zipCode;
    private String state;
    private String city;
    private String addressLine1;
    private String addressLine2;
    private String number;
    private Date dateCreated;
}
