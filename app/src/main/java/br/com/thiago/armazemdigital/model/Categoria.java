package br.com.thiago.armazemdigital.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Categoria {
    private Long id;
    private String name;
    private String description;
    private String dateCreated;
}
