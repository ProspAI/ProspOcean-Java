package br.com.prospocean.dto.response;

import br.com.prospocean.model.Notificacao;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class NotificacaoResponseDTO {

    private UUID id;
    private String mensagem;
    private LocalDateTime timestamp;
    private Notificacao.Tipo tipo;
    private UUID incidentReportId;
    private UUID residuosId;
    private UUID conteudoEducacionalId;
    private UUID centroReciclagemId;
    private UUID especieMarinhaId;

    public NotificacaoResponseDTO(UUID id, String mensagem, LocalDateTime timestamp, Notificacao.Tipo tipo, UUID uuid, UUID uuid1, UUID uuid2, UUID uuid3, UUID uuid4) {
        this.id = id;
        this.mensagem = mensagem;
        this.timestamp = timestamp;
        this.tipo = tipo;
        this.incidentReportId = uuid;
        this.residuosId = uuid1;
        this.centroReciclagemId = uuid2;
        this.especieMarinhaId = uuid3;

    }


}
