package br.com.thiago.armazemdigital.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Fornecedor {
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
    private String dateCreated;
}
