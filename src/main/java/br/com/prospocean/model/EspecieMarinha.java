package br.com.prospocean.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "especies_marinhas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EspecieMarinha {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @Column(nullable = false)
    private String nomeCientifico;

    @NotNull
    @Column(nullable = false)
    private String nomeComum;

    @NotNull
    @Column(nullable = false)
    private String habitat;

    @NotNull
    @Column(nullable = false)
    private String statusConservacao;

     @OneToMany(mappedBy = "especieMarinha", cascade = CascadeType.ALL)
     private List<Notificacao> notificacoes;
}
