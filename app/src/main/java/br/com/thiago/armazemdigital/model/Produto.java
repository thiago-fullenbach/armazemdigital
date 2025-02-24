package br.com.thiago.armazemdigital.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Produto {
    private Long id;
    private Long categoryId;
    private String name;
    private String description;
    private Long price;
    private Long qtt;
    private String dateCreated;
}
