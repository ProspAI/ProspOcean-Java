package br.com.prospocean.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "notificacoes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @Column(nullable = false)
    private String mensagem;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime timestamp;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @ManyToOne
    @JoinColumn(name = "incident_report_id")
    private IncidentReport incidentReport;

    @ManyToOne
    @JoinColumn(name = "residuos_id")
    private Residuos residuos;

    @ManyToOne
    @JoinColumn(name = "conteudo_educacional_id")
    private ConteudoEducacional conteudoEducacional;

    @ManyToOne
    @JoinColumn(name = "centro_reciclagem_id")
    private CentroReciclagem centroReciclagem;

    @ManyToOne
    @JoinColumn(name = "especie_marinha_id")
    private EspecieMarinha especieMarinha;

    public enum Tipo {
        ALERTA, INFORMATIVA, CRITICA
    }
}
