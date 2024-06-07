package br.com.prospocean.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


import java.util.UUID;

@Entity
@Table(name = "usuarios_gs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @Column(nullable = false)
    private String nome;

    @NotNull
    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull
    @Column(nullable = false)
    private String senha;
}
