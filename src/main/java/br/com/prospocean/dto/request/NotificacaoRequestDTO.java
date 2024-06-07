package br.com.prospocean.dto.request;

import br.com.prospocean.model.Notificacao;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class NotificacaoRequestDTO {

    @NotNull
    private String mensagem;

    @NotNull
    private LocalDateTime timestamp;

    @NotNull
    private Notificacao.Tipo tipo;

    private UUID incidentReportId;
    private UUID residuosId;
    private UUID conteudoEducacionalId;
    private UUID centroReciclagemId;
    private UUID especieMarinhaId;
}
