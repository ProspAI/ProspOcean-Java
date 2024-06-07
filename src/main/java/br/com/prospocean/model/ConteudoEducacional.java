package br.com.prospocean.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "conteudos_educacionais")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConteudoEducacional {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @Column(nullable = false)
    private String titulo;

    @NotNull
    @Column(nullable = false)
    private String descricao;

    @NotNull
    @Column(nullable = false)
    private String url;

    @OneToMany(mappedBy = "conteudoEducacional", cascade = CascadeType.ALL)
    private List<Notificacao> notificacoes;
}
