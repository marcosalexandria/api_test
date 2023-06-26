package br.com.dicas.api_test.domain;

import jakarta.persistence.*;
import lombok.*;

@Data //para getter, setter, to string, hascode e equals
@AllArgsConstructor //construtor com argumentos
@NoArgsConstructor //construtor sem argumentos
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @Column(unique = true)
    private String email;
    private String password;
}