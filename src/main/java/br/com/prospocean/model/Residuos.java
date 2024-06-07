package br.com.prospocean.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "residuos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Residuos {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @Column(nullable = false)
    private String tipo;

    @NotNull
    @Column(nullable = false)
    private String localizacao;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime timestamp;

    @OneToMany(mappedBy = "residuos", cascade = CascadeType.ALL)
    private List<Notificacao> notificacoes;

    public enum Status {
        COLETADO, RECICLADO, DESCARTADO
    }
}
