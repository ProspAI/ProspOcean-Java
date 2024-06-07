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
@Table(name = "incident_reports")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncidentReport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @Column(nullable = false)
    private String descricao;

    @NotNull
    @Column(nullable = false)
    private String localizacao;

    @ElementCollection
    private List<String> fotos;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime timestamp;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "incidentReport", cascade = CascadeType.ALL)
    private List<Notificacao> notificacoes;

    public enum Status {
        PENDENTE, EM_ANDAMENTO, RESOLVIDO
    }
}
