package br.com.thiago.armazemdigital.model;

import androidx.room.Entity;
import androidx.room.Ignore;
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
    private String neighborhood;
    private String addressLine1;
    private String addressLine2;
    private String number;
    private Date dateCreated;

    @Ignore
    public Fornecedor(String name, String phone, String email, String zipCode, String state, String city, String neighborhood, String addressLine1, String addressLine2, String number, Date dateCreated) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.zipCode = zipCode;
        this.state = state;
        this.city = city;
        this.neighborhood = neighborhood;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.number = number;
        this.dateCreated = dateCreated;
    }

    @Ignore
    public Fornecedor(String name, String phone, String email, String zipCode, String state, String city, String neighborhood, String addressLine1, String addressLine2, String number) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.zipCode = zipCode;
        this.state = state;
        this.city = city;
        this.neighborhood = neighborhood;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.number = number;
    }
}
