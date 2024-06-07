package br.com.prospocean.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


import java.util.UUID;

@Entity
@Table(name = "centros_reciclagem")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CentroReciclagem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @Column(nullable = false)
    private String nome;

    @NotNull
    @Column(nullable = false)
    private String endereco;

    @NotNull
    @Column(nullable = false)
    private String telefone;

    @NotNull
    @Column(nullable = false)
    private String email;
}
